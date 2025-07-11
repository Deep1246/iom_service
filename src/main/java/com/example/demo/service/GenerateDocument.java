package com.example.demo.service;

import com.example.demo.repository.IomRequestRepository;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Service
public class GenerateDocument {

    @Autowired
    IomRequestRepository iomRequestRepository;

    @Value("${filepath}")
    String filepath;

    public byte[] generate(String documentName, Map<String,Object> params){

        var template = new File(filepath+"\\"+documentName);

        FileInputStream file;

        try{
            file = new FileInputStream(template);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        XWPFDocument document;
        try {
            document = new XWPFDocument(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(params.isEmpty()){
            throw new RuntimeException("Please provide parameters");
        }

        var paragraphs = document.getParagraphs();

        paragraphs.parallelStream().forEach(p -> replace(p, params));

        var tables = document.getTables();
        tables.stream().forEach(table -> {
            table.getRows().stream().forEach(row ->{
                row.getTableCells().stream().forEach(cell -> {
                    cell.getParagraphs().parallelStream().forEach(
                            para -> replace(para, params)
                    );
                });
            });
        });

        var output = new ByteArrayOutputStream();

        try{
            document.write(output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            output.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return output.toByteArray();
    }

    private void replace(XWPFParagraph paragraph, Map<String,Object> params){
        paragraph.getRuns().stream().forEach(run ->{
            var text = run.getText(0);
            if(Objects.nonNull(text)){
                for(var entry : params.entrySet()){
                    if(text.contains(entry.getKey())){
                        text = text.replace(entry.getKey(), String.valueOf(entry.getValue()));
                    }
                    run.setText(text,0);
                }
            }
        });
    }

    public byte[] getData(String requestId){
        var data = iomRequestRepository.findAll(requestId);

        if(data.isEmpty()){
            return null;
        }

        Map<String,Object> record = data.getFirst();



        Map<String,Object> params = new HashMap<>();

        if(Objects.isNull(record.get("form_type"))){
            return null;
        }
        String documentName = record.get("form_type").toString();



//        params.put("date", "17-03-2000");
//        params.put("", "Sending Monthly reports and risk foreseen to all PMS client");
//        params.put("annexure", "Commercials are mentioned under Annexure");
//        params.put("orderValue", "INR 10,300.00 + Taxes extra as applicable");
//        params.put("costCenter", "IT - Infrastructure");
//        params.put("subCostCenter", "PMS Operations");
//        params.put("scopeOfWork", "Sending Monthly reports and risk foreseen to all PMS clientUsername: Brijmohan Shah");
//        params.put("description", "Mail merge toolkit Pro 1 user 1 year license");
//        params.put("L1Name", "Micropoint");
//        params.put("L2Name", "Crayon");
//        params.put("L3Name", "Noventiq");
//        params.put("L1Price", "15000");
//        params.put("L2Price", "20000");
//        params.put("L3Price", "30000");
//        params.put("L1Total", "15000");
//        params.put("L2Total", "20000");
//        params.put("L3Total", "30000");
//        params.put("initiatedBy", "30000");
//        params.put("reviewedBy", "30000");
//        params.put("approvedBy", "30000");

//
        if(documentName.equalsIgnoreCase("hardware")){
            if(Objects.nonNull(record.get("contractparty"))){
                params.put("contractParty", record.get("contractparty"));

            }
            if(Objects.nonNull(record.get("briefdescription"))){
                params.put("briefDescription", record.get("briefdescription"));
            }
            if(Objects.nonNull(record.get("annexure"))){
                params.put("annexure", record.get("annexure"));
            }
            if(Objects.nonNull(record.get("ordervalue"))){
                params.put("orderValue", record.get("ordervalue"));
            }
            if(Objects.nonNull(record.get("costcenter"))){
                params.put("costCenter", record.get("costcenter"));
            }
            if(Objects.nonNull(record.get("subcostcenter"))){
                params.put("subCostCenter", record.get("subcostcenter"));
            }
            if(Objects.nonNull(record.get("briefdescription"))){
                params.put("briefDescription", record.get("briefdescription"));
            }
            if(Objects.nonNull(record.get("scopeofwork"))){
                params.put("scopeOfWork", record.get("scopeofwork"));
            }
            if(Objects.nonNull(record.get("description"))){
                params.put("description", record.get("description"));
            }
            if(Objects.nonNull(record.get("l1name"))){
                params.put("L1Name", record.get("l1name"));
            }
            if(Objects.nonNull(record.get("l2name"))){
                params.put("L2Name", record.get("l2name"));
            }
            if(Objects.nonNull(record.get("l3name"))){
                params.put("L3Name", record.get("l3name"));
            }
            if(Objects.nonNull(record.get("l1price"))){
                params.put("L1Price", record.get("l1price"));
            }
            if(Objects.nonNull(record.get("l2price"))){
                params.put("L2Price", record.get("l2price"));
            }
            if(Objects.nonNull(record.get("l3price"))){
                params.put("L3Price", record.get("l3price"));
            }
            if(Objects.nonNull(record.get("l3total"))){
                params.put("L3Total", record.get("l3total"));
            }
            if(Objects.nonNull(record.get("l2total"))){
                params.put("L2Total", record.get("l2total"));
            }
            if(Objects.nonNull(record.get("l1total"))){
                params.put("L1Total", record.get("l1total"));
            }

            if(Objects.nonNull(record.get("initiatedby"))){
                params.put("initiatedBy", record.get("initiatedby"));
            }
            if(Objects.nonNull(record.get("reviewedby"))){
                params.put("reviewedBy", record.get("reviewedby"));
            }
            if(Objects.nonNull(record.get("approvedby"))){
                params.put("approvedBy", record.get("approvedby"));
            }

            if (Objects.nonNull(record.get("roleinitiated"))) {
                params.put("Roleinitiated", record.get("roleinitiated"));
            }
            if (Objects.nonNull(record.get("rolereview"))) {
                params.put("Rolereview", record.get("rolereview"));
            }
            if (Objects.nonNull(record.get("roleapprove"))) {
                params.put("Roleapprove", record.get("roleapprove"));
            }
            return generate("hardware.docx",params);
        }else {
            if (Objects.nonNull(record.get("contractparty"))) {
                params.put("contractParty", record.get("contractparty"));
            }
            if (Objects.nonNull(record.get("briefdescription"))) {
                params.put("briefDescription", record.get("briefdescription"));
            }
            if (Objects.nonNull(record.get("annexure"))) {
                params.put("annexure", record.get("annexure"));
            }
            if (Objects.nonNull(record.get("amount"))) {
                params.put("amount", record.get("amount"));
            }
            if (Objects.nonNull(record.get("contractperiod"))) {
                params.put("contractPeriod", record.get("contractperiod"));
            }
            if (Objects.nonNull(record.get("budgeted"))) {
                params.put("budgeted", record.get("budgeted"));
            }
            if (Objects.nonNull(record.get("costcenter"))) {
                params.put("costCenter", record.get("costcenter"));
            }
            if (Objects.nonNull(record.get("subcostcenter"))) {
                params.put("subCostCenter", record.get("subcostcenter"));
            }
            if (Objects.nonNull(record.get("recommendationandrationale"))) {
                params.put("recommendationAndRationale", record.get("recommendationandrationale"));
            }
            if (Objects.nonNull(record.get("scopeofwork"))) {
                params.put("scopeOfWork", record.get("scopeofwork"));
            }
            if (Objects.nonNull(record.get("comparativecostanalysis"))) {
                params.put("comparativeCostAnalysis", record.get("comparativecostanalysis"));
            }
            if (Objects.nonNull(record.get("initiatedby"))) {
                params.put("initiatedBy", record.get("initiatedby"));
            }
            if (Objects.nonNull(record.get("reviewedby"))) {
                params.put("reviewedBy", record.get("reviewedby"));
            }
            if (Objects.nonNull(record.get("approvedby"))) {
                params.put("approvedBy", record.get("approvedby"));
            }
            if (Objects.nonNull(record.get("roleinitiated"))) {
                params.put("Roleinitiated", record.get("roleinitiated"));
            }
            if (Objects.nonNull(record.get("rolereview"))) {
                params.put("Rolereview", record.get("rolereview"));
            }
            if (Objects.nonNull(record.get("roleapprove"))) {
                params.put("Roleapprove", record.get("roleapprove"));
            }
            if (Objects.nonNull(record.get("description"))) {
                params.put("description", record.get("description"));
            }
            if (Objects.nonNull(record.get("cmc"))) {
                params.put("CMC", record.get("cmc"));
            }
            if (Objects.nonNull(record.get("rmc"))) {
                params.put("RMC", record.get("rmc"));
            }
            if (Objects.nonNull(record.get("tmc"))) {
                params.put("TMC", record.get("tmc"));
            }
            return generate("software.docx",params);
        }


    }
}

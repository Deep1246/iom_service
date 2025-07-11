package com.example.demo.service;

import com.example.demo.entity.Requests;
import com.example.demo.repository.RequestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RequestsService {

    @Autowired
    public RequestsRepository requestsRepository;

    public Optional<Requests> findRequestById(int id) {
       return requestsRepository.findById(id);
    }

    public boolean updateData(int id, Requests input) {
        Optional<Requests> existingOpt = requestsRepository.findByRequestId(id);
        if (existingOpt.isEmpty()) return false;

        Requests existing = existingOpt.get();

        // --- Only update if non-null and not empty ---
        if (isPresent(input.getTitle())) existing.setTitle(input.getTitle());
        if (isPresent(input.getCategory())) existing.setCategory(input.getCategory());
        if (isPresent(input.getSubCategory())) existing.setSubCategory(input.getSubCategory());
        if (isPresent(input.getDescription())) existing.setDescription(input.getDescription());
        if (input.getRequesterId() != null) existing.setRequesterId(input.getRequesterId());
        if (isPresent(input.getStageId())) existing.setStageId(input.getStageId());
        if (isPresent(input.getContractParty())) existing.setContractParty(input.getContractParty());
        if (isPresent(input.getBriefDescription())) existing.setBriefDescription(input.getBriefDescription());
        if (isPresent(input.getAnnexure())) existing.setAnnexure(input.getAnnexure());
        if (isPresent(input.getCostCenter())) existing.setCostCenter(input.getCostCenter());
        if (isPresent(input.getSubCostCenter())) existing.setSubCostCenter(input.getSubCostCenter());
        if (isPresent(input.getScopeOfWork())) existing.setScopeOfWork(input.getScopeOfWork());
        if (isPresent(input.getRecommendationAndRationale())) existing.setRecommendationAndRationale(input.getRecommendationAndRationale());
        if (isPresent(input.getInitiatedBy())) existing.setInitiatedBy(input.getInitiatedBy());
        if (isPresent(input.getReviewedBy())) existing.setReviewedBy(input.getReviewedBy());
        if (isPresent(input.getApprovedBy())) existing.setApprovedBy(input.getApprovedBy());
        if (isPresent(input.getFormType())) existing.setFormType(input.getFormType());

        // --- Hardware/Software specific ---
        if (isPresent(input.getDate())) existing.setDate(input.getDate());
        if (isPresent(input.getOrderValue())) existing.setOrderValue(input.getOrderValue());
        if (isPresent(input.getL1Name())) existing.setL1Name(input.getL1Name());
        if (isPresent(input.getL2Name())) existing.setL2Name(input.getL2Name());
        if (isPresent(input.getL3Name())) existing.setL3Name(input.getL3Name());
        if (isPresent(input.getL1Price())) existing.setL1Price(input.getL1Price());
        if (isPresent(input.getL2Price())) existing.setL2Price(input.getL2Price());
        if (isPresent(input.getL3Price())) existing.setL3Price(input.getL3Price());
        if (isPresent(input.getL1Total())) existing.setL1Total(input.getL1Total());
        if (isPresent(input.getL2Total())) existing.setL2Total(input.getL2Total());
        if (isPresent(input.getL3Total())) existing.setL3Total(input.getL3Total());
        if (isPresent(input.getAmount())) existing.setAmount(input.getAmount());
        if (isPresent(input.getContractPeriod())) existing.setContractPeriod(input.getContractPeriod());
        if (isPresent(input.getBudgeted())) existing.setBudgeted(input.getBudgeted());
        if (isPresent(input.getComparativeCostAnalysis())) existing.setComparativeCostAnalysis(input.getComparativeCostAnalysis());
        if (isPresent(input.getRoleInitiated())) existing.setRoleInitiated(input.getRoleInitiated());
        if (isPresent(input.getToleReview())) existing.setToleReview(input.getToleReview());
        if (isPresent(input.getRoleApprove())) existing.setRoleApprove(input.getRoleApprove());
        if (isPresent(input.getCmc())) existing.setCmc(input.getCmc());
        if (isPresent(input.getRmc())) existing.setRmc(input.getRmc());
        if (isPresent(input.getTmc())) existing.setTmc(input.getTmc());
        if (isPresent(input.getSelectedVendor())) existing.setSelectedVendor(input.getSelectedVendor());

        requestsRepository.save(existing);
        return true;
    }

    private boolean isPresent(String value) {
        return value != null && !value.trim().isEmpty();
    }
}

package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the "requests" table in the database, optimized with Lombok.
 * This entity is designed to handle both hardware and software request forms,
 * with specific fields being nullable depending on the form_type.
 * Lombok's @Data annotation generates getters, setters, toString, equals, and hashCode methods.
 * Lombok's @NoArgsConstructor is required by JPA/Hibernate.
 */
@Entity
@Table(name = "requests")
@Data
@NoArgsConstructor
public class Requests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Long requestId;

    // --- Common Columns for All Request Types ---

    @Column(name = "title")
    private String title;

    @Column(name = "category")
    private String category;

    @Column(name = "description")
    private String description;

    @Column(name = "sub_category")
    private String subCategory;

    @Column(name = "requester_id")
    private Integer requesterId;

    @Column(name = "stage_id")
    private String stageId;

    @Column(name = "contractParty")
    private String contractParty;

    @Column(name = "briefDescription")
    private String briefDescription;

    @Column(name = "annexure")
    private String annexure;

    @Column(name = "costCenter")
    private String costCenter;

    @Column(name = "subCostCenter")
    private String subCostCenter;

    @Column(name = "scopeOfWork")
    private String scopeOfWork;

    @Column(name = "recommendationAndRationale")
    private String recommendationAndRationale;

    @Column(name = "initiatedBy")
    private String initiatedBy;

    @Column(name = "reviewedBy")
    private String reviewedBy;

    @Column(name = "approvedBy")
    private String approvedBy;

    /**
     * Distinguishes the form type, e.g., 'hardware' or 'software'.
     * This determines which of the specific fields below are populated.
     */
    @Column(name = "form_type")
    private String formType;

    // --- Hardware-Specific Columns (nullable for software requests) ---

    @Column(name = "date")
    private String date;

    @Column(name = "orderValue")
    private String orderValue;

    @Column(name = "L1Name")
    private String l1Name;

    @Column(name = "L2Name")
    private String l2Name;

    @Column(name = "L3Name")
    private String l3Name;

    @Column(name = "L1Price")
    private String l1Price;

    @Column(name = "L2Price")
    private String l2Price;

    @Column(name = "L3Price")
    private String l3Price;

    @Column(name = "L1Total")
    private String l1Total;

    @Column(name = "L2Total")
    private String l2Total;

    @Column(name = "L3Total")
    private String l3Total;

    // --- Software-Specific Columns (nullable for hardware requests) ---

    @Column(name = "amount")
    private String amount;

    @Column(name = "contractPeriod")
    private String contractPeriod;

    @Column(name = "budgeted")
    private String budgeted;

    @Column(name = "comparativeCostAnalysis")
    private String comparativeCostAnalysis;

    @Column(name = "roleinitiated")
    private String roleInitiated;

    @Column(name = "tolereview")
    private String toleReview;

    @Column(name = "roleapprove")
    private String roleApprove;

    @Column(name = "CMC")
    private String cmc;

    @Column(name = "RMC")
    private String rmc;

    @Column(name = "TMC")
    private String tmc;

    @Column(name = "selectedVendor")
    private String selectedVendor;

    // Getters, setters, and the default constructor are now handled by Lombok.
}

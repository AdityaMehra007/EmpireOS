package com.example.data.model

data class BillionaireTitan(
    val id: String,
    val name: String,
    val netWorthUsd: Double, // in billions
    val country: String,
    val primaryCompany: String,
    val sector: String,
    val sourceOfWealth: String,
    val startingCapital: String,
    val firstMillionAge: Int,
    val firstBillionAge: Int,
    val ownershipStake: String,
    val corporateRevenue: String,
    val corporateNetIncome: String,
    val coreStrategy: String,
    val managementPhilosophy: String,
    val majorFailure: String,
    val dailyRoutineAndLifestyle: String,
    val lessonForAditya: String,
    val isHistorical: Boolean = false
)

data class BusinessEmpire(
    val id: String,
    val name: String,
    val ticker: String,
    val sector: String,
    val foundedYear: Int,
    val founders: String,
    val hq: String,
    val marketCapUsd: Double, // in billions
    val annualRevenueUsd: Double, // in billions
    val netIncomeUsd: Double, // in billions
    val netMarginPct: Double,
    val employeeCount: Int,
    val howItMakesMoney: String,
    val competitiveMoat: String,
    val keyAcquisitions: String,
    val majorRisks: String
)

data class BusinessModelOpportunity(
    val id: Int,
    val name: String,
    val category: String,
    val whoPaysAndWhy: String,
    val startupCapitalUsd: String,
    val startupCapitalInr: String,
    val grossMarginPct: String,
    val netMarginPct: String,
    val scalabilityScore: Double, // out of 10
    val executionDifficulty: String, // Low, Medium, High, Extreme
    val exitMultiple: String,
    val beginnerEntryBangalore: String,
    val rankingPriority: Int // 1 to 5
)

data class EmpireStage(
    val stageNumber: Int,
    val title: String,
    val rangeInr: String,
    val rangeUsd: String,
    val coreObjective: String,
    val primaryBusinessModel: String,
    val startingCapital: String,
    val customerStrategy: String,
    val pricingAndTargetRevenue: String,
    val teamAndHiring: String,
    val techAndAiStack: String,
    val legalAndTaxStructure: String,
    val monthlyMilestones: List<String>,
    val keyRisksAndControls: String
)

data class FrontierTechnology(
    val id: Int,
    val name: String,
    val currentTam2026: String,
    val futureTam2040: String,
    val maturityStage: String,
    val keyPlayers: String,
    val entryBarrier: String,
    val whatToBuildToday: String,
    val horizonYears: Int // 1, 3, 5, 10, 25
)

data class MacroData(
    val usdInrRate: Double = 94.42,
    val fedFundsRate: String = "3.88% (Range 3.75%–4.00%)",
    val rbiRepoRate: String = "6.75% (Calibrated Tightening)",
    val indiaGdpGrowth: String = "7.2% YoY (Fastest Major Economy)",
    val globalBillionairesCount: Int = 3428,
    val globalBillionaireWealth: String = "$20.1 Trillion",
    val nvidiaMarketCap: String = "$4.43T – $5.72T",
    val alphabetNetIncome: String = "$100.12B / yr",
    val appleMarketCap: String = "$4.90T – $5.04T",
    val muskWorth: String = "$839 Billion"
)

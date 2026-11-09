package com.example.data.repository

import com.example.data.model.BusinessModelOpportunity
import com.example.data.model.EmpireStage
import com.example.data.model.FrontierTechnology

object RoadmapRepository {

    val stages: List<EmpireStage> = listOf(
        EmpireStage(
            stageNumber = 1,
            title = "Proof of Commercial Value",
            rangeInr = "₹0 to ₹1 Lakh",
            rangeUsd = "$0 to $1,060",
            coreObjective = "Acquire high-leverage technical AI agent skills, build 3 verifiable workflow prototypes, close first 1-3 paying clients, and bank ₹1,00,000 in pure cash in 30-45 days.",
            primaryBusinessModel = "B2B AI Outbound & Workflow Automation Specialist (Freelance/Solo Operator)",
            startingCapital = "₹0 to ₹5,000 (Existing laptop + internet + trial tiers of n8n, Make, Clay, Apollo, OpenAI/Claude)",
            customerStrategy = "Send 50 personalized Loom demo videos/day to US/UK/UAE B2B agency and SaaS founders offering a free 7-day workflow deployment, charging ₹25,000-₹50,000 ($300-$500) only after value is proven.",
            pricingAndTargetRevenue = "₹25,000 to ₹50,000 per setup. Goal: 3 projects = ₹1,00,000 cash.",
            teamAndHiring = "Solo founder. You are the SDR, engineer, and account manager.",
            techAndAiStack = "n8n, Make.com, Clay.com, Apollo.io, Claude 3.7/4 Sonnet, OpenAI API, Loom.",
            legalAndTaxStructure = "Sole Proprietorship / Individual PAN billing. Dedicated current/savings account.",
            monthlyMilestones = listOf(
                "Days 1-10: Master n8n/Clay/Make agent workflows and build 3 production demos.",
                "Days 11-25: 500 targeted outbound messages -> 15 discovery calls booked.",
                "Days 26-40: Deliver 2 pilot workflows, collect first testimonials and ₹1,00,000."
            ),
            keyRisksAndControls = "Risk: Analysis paralysis. Control: Send minimum 30 outbound messages daily before opening Twitter/YouTube."
        ),
        EmpireStage(
            stageNumber = 2,
            title = "Recurring Retainer Engine",
            rangeInr = "₹1 Lakh to ₹10 Lakhs",
            rangeUsd = "$1,060 to $10,600",
            coreObjective = "Transition from one-off projects to recurring international retainers ($1,500-$2,500/mo) under 'Mehra Global AI Systems'.",
            primaryBusinessModel = "Productized B2B AI Revenue & Operations Agency",
            startingCapital = "₹50,000 reinvested from Stage 1 cash flow.",
            customerStrategy = "Convert Stage 1 clients to $1,500/mo management retainers. Run automated cold outbound (Instantly/Smartlead) sending 500 emails/day to US/UAE B2B firms.",
            pricingAndTargetRevenue = "$1,500 to $2,500/month retainer. Target: 5 active retainers = ₹7L-₹10L/month run rate.",
            teamAndHiring = "Hire 1-2 part-time student engineering interns in Bangalore (RVCE/BMS/PES) at ₹15,000-₹20,000/month on milestone deliverables.",
            techAndAiStack = "Smartlead, Instantly, Vapi.ai (voice agents), Make, Supabase, Airtable, Notion.",
            legalAndTaxStructure = "Register GST + GST LUT (0% export tax on software/services exports), IEC, receive USD via Skydo/Wise Business with e-BRC/FIRA.",
            monthlyMilestones = listOf(
                "Month 1: Sign 2 retainer clients at $1,500/mo.",
                "Month 2: Onboard 3 more retainers, hire first Bangalore technical intern.",
                "Month 3: Hit ₹10 Lakhs cumulative net profit; automate client reporting."
            ),
            keyRisksAndControls = "Risk: Client churn. Control: Weekly quantified ROI reports showing leads booked or hours saved."
        ),
        EmpireStage(
            stageNumber = 3,
            title = "Systematized Agency & Scaling",
            rangeInr = "₹10 Lakhs to ₹1 Crore",
            rangeUsd = "$10,600 to $106,000",
            coreObjective = "Build a 6-10 person delivery pod in Bangalore, scale to 25-40 international retainers, and cross ₹1 Crore cumulative net profit.",
            primaryBusinessModel = "Specialized Vertical AI Transformation & Cross-Border B2B Growth Partner",
            startingCapital = "₹5 Lakhs - ₹10 Lakhs reinvested from retained earnings (Zero equity dilution).",
            customerStrategy = "High-volume outbound + weekly founder thought-leadership content on LinkedIn/YouTube breakdown videos + 15% referral fee to US fractional CFOs.",
            pricingAndTargetRevenue = "$2,500 to $4,000/month retainers. Target: ₹1.8 Cr - ₹2.5 Cr annual revenue, ₹1 Cr net profit.",
            teamAndHiring = "4 AI Automation Engineers, 2 Client Success Managers, 1 Executive Assistant in Bangalore.",
            techAndAiStack = "Custom Python/TypeScript agent microservices, Docker, AWS/GCP, LangChain/LlamaIndex, Linear.",
            legalAndTaxStructure = "Incorporate as Indian Private Limited Company. Zoho Books accrual accounting, monthly CA audits.",
            monthlyMilestones = listOf(
                "Quarter 1: Cross ₹15 Lakhs/month recurring revenue.",
                "Quarter 2: Hire full-time Bangalore engineering team in HSR Layout / Indiranagar.",
                "Quarter 3-4: Cross ₹1 Crore net profit mark with 55%+ net profit margin."
            ),
            keyRisksAndControls = "Risk: Service bottlenecks. Control: Standardize 80% of workflows into reusable internal code modules."
        ),
        EmpireStage(
            stageNumber = 4,
            title = "The Services-to-SaaS Bridge",
            rangeInr = "₹1 Crore to ₹10 Crore",
            rangeUsd = "$106k to $1.06M",
            coreObjective = "Extract the most repeated agency workflow into a proprietary Vertical AI SaaS platform (e.g. TradeFlow AI for global trade compliance & freight).",
            primaryBusinessModel = "Hybrid Cash-Cow Agency + Vertical AI SaaS Platform",
            startingCapital = "₹50 Lakhs - ₹1 Crore self-funded from agency profits (100% founder ownership maintained!).",
            customerStrategy = "Migrate 30 existing agency clients as paid design partners ($500-$2,000/mo), scale to 300+ global B2B SaaS accounts.",
            pricingAndTargetRevenue = "$500 to $2,500/month SaaS subscription. Target: $1.5M-$2.5M ARR (₹14 Cr-₹24 Cr revenue).",
            teamAndHiring = "25-35 people in Bangalore: VP of Engineering, Senior Backend/AI Leads, US-time-zone Account Executives.",
            techAndAiStack = "Next.js, FastAPI, PostgreSQL/pgvector, Kubernetes, Stripe Billing, SOC 2 compliance.",
            legalAndTaxStructure = "Evaluate Delaware C-Corp / Singapore HoldCo structure (compliant with RBI FEMA ODI rules) with Bangalore R&D subsidiary.",
            monthlyMilestones = listOf(
                "Months 1-4: Build MVP with design partners.",
                "Months 5-8: Launch v1.0, reach $50,000 MRR from software alone.",
                "Months 9-12: Surpass $1.5M ARR; enterprise valuation crosses ₹75 Cr-₹100 Cr."
            ),
            keyRisksAndControls = "Risk: Running out of cash for SaaS R&D. Control: Agency cash cow must maintain minimum ₹10L/mo free cash flow cushion."
        ),
        EmpireStage(
            stageNumber = 5,
            title = "Centimillionaire Trajectory",
            rangeInr = "₹10 Crore to ₹100 Crore",
            rangeUsd = "$1.06M to $10.6M",
            coreObjective = "Scale SaaS + Data Platform to $10M+ ARR (₹95+ Crore) with embedded fintech (0.5% take rate on cross-border payments).",
            primaryBusinessModel = "Vertical AI Platform + B2B Cross-Border Financial Infrastructure",
            startingCapital = "Internal Free Cash Flow + optional non-dilutive debt or minority growth equity (<15%).",
            customerStrategy = "1,000+ mid-market corporate clients paying $10,000-$25,000 ACV across North America, Europe, UAE, and Asia.",
            pricingAndTargetRevenue = "SaaS licenses + 0.5% payment volume take-rate. Target: ₹100 Cr-₹140 Cr revenue, ₹35 Cr EBITDA.",
            teamAndHiring = "80-120 people: US/Dubai direct enterprise sales offices + core engineering/product campus in Bangalore.",
            techAndAiStack = "Proprietary fine-tuned foundation models, enterprise ERP integrations (SAP/Oracle/NetSuite).",
            legalAndTaxStructure = "GIFT City IFSC treasury vehicle for global dollar repatriation; multi-jurisdiction tax optimization.",
            monthlyMilestones = listOf(
                "Year 1: Scale from $3M to $7M ARR.",
                "Year 2: Launch embedded cross-border settlement rails.",
                "Year 3: Cross $12M ARR; enterprise valuation reaches ₹600 Cr-₹800 Cr ($65M-$85M)."
            ),
            keyRisksAndControls = "Risk: Enterprise sales cycle elongation. Control: Strict pilot-to-contract conversion criteria with non-refundable deposits."
        ),
        EmpireStage(
            stageNumber = 6,
            title = "Centimillionaire Group & Roll-Up",
            rangeInr = "₹100 Crore to ₹1,000 Crore",
            rangeUsd = "$10.6M to $106M",
            coreObjective = "Cross $100M Net Worth by executing a programmatic roll-up of niche B2B software and specialized service businesses.",
            primaryBusinessModel = "Constellation-Style Vertical Software & AI Infrastructure Conglomerate",
            startingCapital = "SaaS free cash flow + structured non-callable institutional credit.",
            customerStrategy = "Acquire 3-5 profitable $3M-$8M ARR niche B2B software firms at 4x-6x EBITDA, inject your Bangalore AI engine to double margins to 45%, re-rate at 15x-20x EBITDA.",
            pricingAndTargetRevenue = "Consolidated revenue: ₹400 Cr-₹600 Cr | Net profit: ₹120 Cr-₹180 Cr ($15M-$20M/yr).",
            teamAndHiring = "Central M&A / Capital Allocation team (4-6 investment bankers/analysts) + decentralized business unit leaders.",
            techAndAiStack = "Unified data warehouse, standardized cross-portfolio AI automation pipelines.",
            legalAndTaxStructure = "Holding Company structure with cross-collateralized subsidiary credit lines.",
            monthlyMilestones = listOf(
                "Year 1: Complete first two tuck-in acquisitions ($10M total EV).",
                "Year 2: Expand EBITDA margins from 22% to 42% via shared AI infrastructure.",
                "Year 3: Group valuation crosses $250M (₹2,350 Cr); Aditya's equity exceeds ₹1,500 Cr ($160M)."
            ),
            keyRisksAndControls = "Risk: Integration failure / culture clash. Control: Keep acquired company leadership in place with earn-outs; only automate back-office operations."
        ),
        EmpireStage(
            stageNumber = 7,
            title = "Public Listing & Billionaire Status",
            rangeInr = "₹1,000 Crore to ₹10,000 Crore",
            rangeUsd = "$106M to $1.06B",
            coreObjective = "IPO on NSE/BSE or NASDAQ; deploy permanent software cash flows into AI data centers, power infrastructure, and financial services.",
            primaryBusinessModel = "Publicly Listed Tech Platform + Private Equity Holding Company ('Mehra Global Holdings')",
            startingCapital = "Public market equity & permanent institutional capital.",
            customerStrategy = "Global enterprise dominance, government/defense contracts, institutional asset management.",
            pricingAndTargetRevenue = "Revenue: ₹2,500 Cr-₹4,000 Cr | Net Profit: ₹600 Cr-₹950 Cr ($65M-$100M/yr net profit).",
            teamAndHiring = "Professional executive board, independent directors, world-class CIO and legal counsel.",
            techAndAiStack = "Hyperscale AI compute infrastructure, green energy data center parks in Karnataka/Tamil Nadu.",
            legalAndTaxStructure = "Promoter family trust holding >51% voting equity; dual-class shares; multi-family office oversight.",
            monthlyMilestones = listOf(
                "Phase 1: File DRHP for IPO at ₹12,000 Cr ($1.3B) valuation.",
                "Phase 2: Successful public listing; retain 50%+ promoter stake.",
                "Phase 3: Aditya's net worth crosses ₹10,000 Crore ($1.06 Billion), joining Forbes India 100 Richest."
            ),
            keyRisksAndControls = "Risk: Public market volatility and short sellers. Control: High promoter ownership, low net debt (<1.0x EBITDA), fortress cash reserves."
        ),
        EmpireStage(
            stageNumber = 8,
            title = "Global Sovereign Empire",
            rangeInr = "₹10,000 Crore+ ($1B to $100B+)",
            rangeUsd = "$1 Billion to $100 Billion+",
            coreObjective = "Sovereign-Scale Capital Allocation, permanent holding company compounding across Energy, Compute, Defense, and Financial Float.",
            primaryBusinessModel = "Multigenerational Permanent Capital Conglomerate (Modeled on Berkshire, Danaher, Reliance, Adani)",
            startingCapital = "Multi-billion sovereign & institutional permanent capital.",
            customerStrategy = "Essential infrastructure that nations and global industries cannot function without.",
            pricingAndTargetRevenue = "Tens of billions in annual operating cash flows.",
            teamAndHiring = "Decentralized autonomous CEOs, world-class capital allocation committee.",
            techAndAiStack = "Next-gen fusion/nuclear SMR data centers, autonomous defense networks, global trade rails.",
            legalAndTaxStructure = "Perpetual family trust constitution, sovereign asset protection, philanthropic foundation endowment.",
            monthlyMilestones = listOf(
                "Decade 1-2: Compound consolidated book value at 20%+ per annum.",
                "Explore the economics of trillion-dollar enterprise ecosystems.",
                "Establish permanent philanthropic and scientific research foundations in India."
            ),
            keyRisksAndControls = "Risk: Succession failure & family disputes. Control: Strict family constitution prohibiting equity liquidation without 80% council consent."
        )
    )

    val topBusinessModels: List<BusinessModelOpportunity> = listOf(
        BusinessModelOpportunity(
            id = 3,
            name = "AI Agents & Autonomous BPO (Outcome-as-a-Service)",
            category = "Artificial Intelligence",
            whoPaysAndWhy = "Companies paying per resolved ticket, qualified lead, or reconciled invoice (displacing $40k/yr human seats).",
            startupCapitalUsd = "$50 – $500",
            startupCapitalInr = "₹5,000 – ₹50,000",
            grossMarginPct = "75% – 90%",
            netMarginPct = "40% – 60%",
            scalabilityScore = 9.8,
            executionDifficulty = "Medium",
            exitMultiple = "6x – 12x ARR",
            beginnerEntryBangalore = "BEST ENTRY FOR ADITYA: Launch 'Mehra Global AI Systems'. Build automated outbound lead engines & 24/7 AI voice qualification pipelines for US/UAE B2B firms.",
            rankingPriority = 1
        ),
        BusinessModelOpportunity(
            id = 41,
            name = "AI Transformation & Enterprise Consulting",
            category = "Professional Services",
            whoPaysAndWhy = "Mid-market CEOs & CFOs paying $10k-$50k/mo to modernize legacy workflows and cut overhead with AI.",
            startupCapitalUsd = "$100",
            startupCapitalInr = "₹10,000",
            grossMarginPct = "70% – 85%",
            netMarginPct = "35% – 50%",
            scalabilityScore = 8.5,
            executionDifficulty = "Medium",
            exitMultiple = "8x – 14x EBITDA",
            beginnerEntryBangalore = "Pair BBA International Business consultative credibility with no-code AI automation stacks to sell fixed-price workflow transformations.",
            rankingPriority = 1
        ),
        BusinessModelOpportunity(
            id = 1,
            name = "B2B Software as a Service (SaaS)",
            category = "Software",
            whoPaysAndWhy = "Enterprises paying monthly/annual subscriptions for mission-critical systems of record.",
            startupCapitalUsd = "$1,000 – $20,000",
            startupCapitalInr = "₹1 Lakh – ₹18 Lakhs",
            grossMarginPct = "80% – 88%",
            netMarginPct = "25% – 40%",
            scalabilityScore = 9.5,
            executionDifficulty = "High",
            exitMultiple = "8x – 15x ARR",
            beginnerEntryBangalore = "Build Vertical AI SaaS for cross-border export logistics or freight documentation after running your agency for 6-12 months.",
            rankingPriority = 1
        ),
        BusinessModelOpportunity(
            id = 5,
            name = "Proprietary B2B Data Infrastructure",
            category = "Data & Intelligence",
            whoPaysAndWhy = "Exporters, hedge funds, and supply-chain heads paying for real-time verified customs/supplier datasets.",
            startupCapitalUsd = "$2,000 – $10,000",
            startupCapitalInr = "₹2 Lakhs – ₹10 Lakhs",
            grossMarginPct = "85% – 95%",
            netMarginPct = "45% – 65%",
            scalabilityScore = 9.2,
            executionDifficulty = "Medium",
            exitMultiple = "10x – 20x ARR",
            beginnerEntryBangalore = "Scrape, verify, and enrich Indian supplier & export regulatory data for Western buyers seeking 'China+1' partners.",
            rankingPriority = 2
        ),
        BusinessModelOpportunity(
            id = 14,
            name = "Private Equity & Micro-SaaS Roll-Ups",
            category = "Capital Markets",
            whoPaysAndWhy = "Buying boring cash-flowing B2B software/agencies at 3x-5x EBITDA and consolidating under one platform.",
            startupCapitalUsd = "$50,000 – $500,000",
            startupCapitalInr = "₹50 Lakhs – ₹5 Crore",
            grossMarginPct = "60% – 80%",
            netMarginPct = "30% – 45%",
            scalabilityScore = 9.0,
            executionDifficulty = "High",
            exitMultiple = "12x – 18x EBITDA",
            beginnerEntryBangalore = "Ideal vehicle for Stage 6: roll up 3-5 niche logistics/accounting software tools using agency cash flows.",
            rankingPriority = 3
        ),
        BusinessModelOpportunity(
            id = 23,
            name = "Contract Manufacturing & EMS Sourcing",
            category = "Industrial",
            whoPaysAndWhy = "Western OEMs shifting supply chains from China to India under 'China+1'.",
            startupCapitalUsd = "$1,000 (Brokerage) to $500k (Plant)",
            startupCapitalInr = "₹1 Lakh – ₹5 Crore",
            grossMarginPct = "15% – 35%",
            netMarginPct = "8% – 16%",
            scalabilityScore = 8.5,
            executionDifficulty = "High",
            exitMultiple = "10x – 18x EBITDA",
            beginnerEntryBangalore = "Act as an export sourcing agent connecting US/EU buyers with vetted precision machine shops in Peenya, Pune, and Coimbatore.",
            rankingPriority = 2
        )
    )

    val frontierTechs: List<FrontierTechnology> = listOf(
        FrontierTechnology(
            id = 1,
            name = "Autonomous AI Agent Companies",
            currentTam2026 = "$18 Billion",
            futureTam2040 = "$1.5 Trillion",
            maturityStage = "Early Production Workflows",
            keyPlayers = "OpenAI, Anthropic, Sierra, Cognition, Harvey",
            entryBarrier = "Proprietary enterprise feedback loops & ERP integration",
            whatToBuildToday = "Full-stack autonomous agencies where 90% of lead-gen, fulfillment, and accounting are run by multi-agent loops.",
            horizonYears = 1
        ),
        FrontierTechnology(
            id = 2,
            name = "AI Voice & Multilingual Phone Agents",
            currentTam2026 = "$28 Billion",
            futureTam2040 = "$1.8 Trillion (Displacing legacy BPO)",
            maturityStage = "Sub-300ms latency live in production",
            keyPlayers = "ElevenLabs, Vapi, Bland, Salesforce Agentforce",
            entryBarrier = "Real-time telephony integration & accent adaptation",
            whatToBuildToday = "Deploy multilingual AI voice agents for debt collections, export shipping updates, and clinic appointment booking.",
            horizonYears = 1
        ),
        FrontierTechnology(
            id = 3,
            name = "Humanoid & General-Purpose Robotics",
            currentTam2026 = "$6 Billion",
            futureTam2040 = "$2.0 Trillion+",
            maturityStage = "Factory pilot deployments (Cost curve aiming for <$20k/unit by 2030)",
            keyPlayers = "Tesla Optimus, Figure AI, Boston Dynamics, Unitree",
            entryBarrier = "Actuator precision & Vision-Language-Action (VLA) sim-to-real models",
            whatToBuildToday = "Robotics teleoperation data collection centers in India; precision harmonic-drive actuator component export.",
            horizonYears = 5
        ),
        FrontierTechnology(
            id = 4,
            name = "Small Modular Nuclear Reactors (SMRs) for AI",
            currentTam2026 = "$12 Billion",
            futureTam2040 = "$400 Billion",
            maturityStage = "Hyperscalers (Microsoft, Google, Amazon) signing GW-scale PPAs",
            keyPlayers = "Kairos Power, TerraPower, Oklo, Westinghouse, NPCIL",
            entryBarrier = "Nuclear regulatory licensing (5-10 yrs)",
            whatToBuildToday = "Data center cooling systems, high-voltage substations, and industrial land aggregation for micro-grid tech parks.",
            horizonYears = 10
        ),
        FrontierTechnology(
            id = 5,
            name = "Programmable Stablecoin Settlement Rails",
            currentTam2026 = "$220B+ circulating supply ($15T+ annual volume)",
            futureTam2040 = "$25 Trillion+ tokenized global assets",
            maturityStage = "Stripe acquired Bridge ($1.1B); BlackRock BUIDL live",
            keyPlayers = "Tether, Circle, Stripe, BlackRock, Visa",
            entryBarrier = "Regulatory licensing & compliance",
            whatToBuildToday = "Cross-border B2B export invoice reconciliation and instant settlement treasury tools for Indian exporters.",
            horizonYears = 3
        )
    )
}

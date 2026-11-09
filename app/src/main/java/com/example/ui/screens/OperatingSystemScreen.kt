package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

@Composable
fun OperatingSystemScreen(
    isUsdMode: Boolean
) {
    var selectedSection by remember { mutableIntStateOf(0) }
    val sections = listOf("Wealth OS Routines", "Autonomous AI (<15 Staff)", "Cap Table & Control", "Cross-Border Tax & Legal", "Risk Matrix")

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(ObsidianBg)
            .testTag("operating_system_screen"),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 96.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Header
        item {
            Column {
                Text(
                    text = "PERSONAL WEALTH OS & INSTITUTIONAL TOOLS",
                    color = ImperialGold,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 1.sp
                )
                Text(
                    text = "Sovereign Governance & Systems",
                    color = ChampagneText,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Section Tabs
        item {
            ScrollableTabRow(
                selectedTabIndex = selectedSection,
                containerColor = ObsidianCard,
                contentColor = ImperialGold,
                edgePadding = 0.dp,
                divider = { Divider(color = OutlineBorder) }
            ) {
                sections.forEachIndexed { idx, title ->
                    Tab(
                        selected = selectedSection == idx,
                        onClick = { selectedSection = idx },
                        text = {
                            Text(
                                text = title,
                                fontSize = 12.sp,
                                fontWeight = if (selectedSection == idx) FontWeight.Bold else FontWeight.Normal,
                                color = if (selectedSection == idx) ImperialGold else ChampagneMuted
                            )
                        }
                    )
                }
            }
        }

        when (selectedSection) {
            0 -> {
                // SECTION 0: WEALTH OS ROUTINES (Daily, Weekly, Monthly, Quarterly, Annual)
                item {
                    OSCadenceCard(
                        cadence = "DAILY OPERATING RHYTHM (THE 4-HOUR HIGH-LEVERAGE CORE)",
                        borderColor = ImperialGold,
                        items = listOf(
                            "06:30 - 08:00: Physical Fitness & VO2 Max / Strength Training + Cold whole-food breakfast (Protects 40-year runway).",
                            "08:30 - 11:30: DEEP WORK BLOCK #1: Client Acquisition & Outbound. 30 personalized Loom videos/messages sent. Zero social media.",
                            "12:00 - 14:00: DEEP WORK BLOCK #2: System & Agent Architecture. Building, debugging, or shipping client workflows.",
                            "15:00 - 17:30: Discovery Calls, Client Onboarding, and Bangalore engineering team syncs.",
                            "20:00 - 21:00: 60 minutes of high-signal reading (annual reports, 10-Ks, AI technical documentation, historical biographies)."
                        )
                    )
                }

                item {
                    OSCadenceCard(
                        cadence = "WEEKLY SUNDAY REVIEW (REVENUE & PIPELINE AUDIT)",
                        borderColor = SapphireTech,
                        items = listOf(
                            "Net Cash Flow Audit: Review all bank accounts (Personal + Business). Track exact cash collected vs receivables.",
                            "Pipeline Health: How many outbound sent? How many replies? How many calls? What was the conversion rate?",
                            "Client Delivery Health: Are any client workflows throwing API errors? Did we send weekly quantified ROI reports?",
                            "Opportunity Sifting: Kill 1 low-leverage activity; double down on the single highest-ROI channel."
                        )
                    )
                }

                item {
                    OSCadenceCard(
                        cadence = "MONTHLY FINANCIAL P&L & TAX PROVISIONING",
                        borderColor = EmeraldProfit,
                        items = listOf(
                            "P&L Statement: Realized Revenue, COGS (server/API fees), Salaries/Contractors, Net Profit.",
                            "Transfer 30% of gross profits immediately to dedicated Tax & Working Capital reserve accounts (Never spend tax money!).",
                            "FEMA & Export Compliance: Verify all foreign remittances have e-BRC / FIRA certificates downloaded and filed with CA.",
                            "Reinvestment Rate: Target 80%+ retained cash reinvested into high-ROI software R&D or client acquisition."
                        )
                    )
                }

                item {
                    OSCadenceCard(
                        cadence = "ANNUAL PERSONAL BALANCE SHEET & VALUATION AUDIT",
                        borderColor = ImperialGoldLight,
                        items = listOf(
                            "Calculate true Personal Net Worth: Cash + Liquid Bonds + Equity Valuation (conservatively discounted at 20x P/E) - Liabilities.",
                            "Cap Table Audit: Check dilution, option pool grants, and ensure founder voting control remains >60%+.",
                            "Succession & Asset Protection: Verify promoter trusts, insurance coverage, and corporate veil integrity."
                        )
                    )
                }
            }

            1 -> {
                // SECTION 1: AUTONOMOUS AI ENTERPRISE (<15 EMPLOYEES FOR $100M ARR)
                item {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                        shape = RoundedCornerShape(14.dp),
                        border = BorderStroke(1.5.dp, ImperialGold)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text(
                                text = "THE AUTONOMOUS AI ENTERPRISE ARCHITECTURE",
                                color = ImperialGold,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Black
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Historically, generating $100M ARR required 500-1,200 employees. In 2026-2030, an AI-native company will achieve $100M with fewer than 15 humans by orchestrating digital agent workforces:",
                                color = ChampagneText,
                                fontSize = 12.sp,
                                lineHeight = 16.sp
                            )
                        }
                    }
                }

                listOf(
                    Triple(
                        "1. Automated SDR & Inbound Pipeline",
                        "Agents run 24/7 lead scoring, Clay enrichment, and personalized email/LinkedIn outreach. 1 Human Head of Growth oversees 500,000 monthly contacts.",
                        SapphireTech
                    ),
                    Triple(
                        "2. AI Voice & WhatsApp Support / CSM",
                        "Sub-300ms voice agents (Vapi/ElevenLabs) resolve 85% of tier-1 support and invoice follow-ups. 2 Human Enterprise CSMs handle top-tier relationships.",
                        EmeraldProfit
                    ),
                    Triple(
                        "3. Code & Infrastructure Synthesis",
                        "Engineers write high-level PRDs and system specs; AI code agents (Cursor/Claude) generate, test, and containerize microservices. 5 Engineers do the work of 40.",
                        ImperialGold
                    ),
                    Triple(
                        "4. Autonomous Financial & Compliance Closing",
                        "AI agents parse bank feeds, match invoices, file GST LUT and ICEGATE filings, and produce daily GAAP balance sheets with zero manual bookkeeping.",
                        SapphireTech
                    )
                ).forEach { (title, desc, color) ->
                    item {
                        Card(
                            colors = CardDefaults.cardColors(containerColor = ObsidianCard),
                            shape = RoundedCornerShape(12.dp),
                            border = BorderStroke(1.dp, OutlineBorder)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(text = title, color = color, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = desc, color = ChampagneMuted, fontSize = 12.sp, lineHeight = 16.sp)
                            }
                        }
                    }
                }
            }

            2 -> {
                // SECTION 2: CAP TABLE & CONTROL DEFENSE
                item {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = ObsidianCard),
                        shape = RoundedCornerShape(14.dp),
                        border = BorderStroke(1.dp, OutlineBorder)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text(
                                text = "FOUNDER CAP TABLE ENGINEERING & DILUTION DEFENSE",
                                color = ImperialGold,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            CapTableRule(
                                ruleNum = "RULE 1",
                                title = "Dual-Class Super-Voting Shares",
                                text = "Never surrender voting control for capital. Structure Class A (1 vote per share, for public/investors) and Class B (10 to 20 votes per share, for Aditya). This was how Zuckerberg, Page, Brin, and Musk maintained total strategic authority."
                            )

                            CapTableRule(
                                ruleNum = "RULE 2",
                                title = "Beware Liquidation Preference Traps",
                                text = "Never accept 'Participating Preferred' stock. Insist on 1x Non-Participating Preferred with a capped return. Participating preferred allows investors to get their money back first AND take their percentage of the remaining proceeds, wiping out common founders in modest exits."
                            )

                            CapTableRule(
                                ruleNum = "RULE 3",
                                title = "Bootstrap Until Product-Market Fit",
                                text = "Every 10% you give away at a ₹2 Cr valuation costs you ₹100 Crore at a ₹1,000 Cr valuation. Use agency cash flow (Sweat Equity) to fund the early product so you never raise seed money at predatory valuations."
                            )

                            CapTableRule(
                                ruleNum = "RULE 4",
                                title = "Protective Provisions & Board Seats",
                                text = "Keep a 3-person board (Founder + 1 Trusted Advisor + 1 Investor). Never allow investors to hold majority board control before Series C. Maintain negative covenants over budget approval and CEO replacement."
                            )
                        }
                    }
                }
            }

            3 -> {
                // SECTION 3: CROSS-BORDER TAX & LEGAL ARCHITECTURE
                item {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = ObsidianCard),
                        shape = RoundedCornerShape(14.dp),
                        border = BorderStroke(1.dp, EmeraldProfit.copy(alpha = 0.5f))
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text(
                                text = "CROSS-BORDER LEGAL, FEMA & TAX STRATEGY",
                                color = EmeraldProfit,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            LegalPillar(
                                title = "1. GST LUT (Letter of Undertaking) for 0% Tax on Service Exports",
                                desc = "When exporting AI services and software from Bangalore to US/UK/UAE clients, file an annual GST RFD-11 LUT. This legally exempts your cross-border services from 18% GST without needing cumbersome refund claims."
                            )

                            LegalPillar(
                                title = "2. RBI FEMA & e-BRC / FIRA Reconciliation",
                                desc = "Every dollar remitted from overseas into your Indian bank account must be reconciled via EDPMS with an Electronic Bank Realisation Certificate (e-BRC). Use fintechs like Skydo or Razorpay MoneySaver to receive FIRA automatically at competitive FX spreads (save 2-3%)."
                            )

                            LegalPillar(
                                title = "3. GIFT City IFSC Holding Unit",
                                desc = "Gujarat International Finance Tec-City (GIFT City) offers a 10-year 100% corporate tax holiday for qualifying financial and IT export units, allowing seamless multi-currency dollar treasury management directly within India."
                            )

                            LegalPillar(
                                title = "4. Delaware C-Corp / Singapore Flip (Stages 4–6)",
                                desc = "If taking institutional Silicon Valley VC or preparing for a US IPO, execute an RBI-compliant Overseas Direct Investment (ODI) flip, establishing Delaware or Singapore as the HoldCo while maintaining Bangalore as a wholly-owned R&D cost-plus subsidiary."
                            )
                        }
                    }
                }
            }

            4 -> {
                // SECTION 4: RISK & WEALTH PROTECTION MATRIX
                item {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = ObsidianCard),
                        shape = RoundedCornerShape(14.dp),
                        border = BorderStroke(1.dp, CrimsonRisk.copy(alpha = 0.5f))
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text(
                                text = "WEALTH PROTECTION & RISK MITIGATION MATRIX",
                                color = CrimsonRisk,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            RiskItem(
                                risk = "Customer Concentration Risk",
                                fix = "No single client should ever account for >20% of your business revenue. If one client leaves, the business must survive unscathed."
                            )

                            RiskItem(
                                risk = "Callable Leverage Risk",
                                fix = "NEVER trade stocks, crypto, or real estate on callable bank margin debt. A 30% flash crash forces liquidation at the exact bottom."
                            )

                            RiskItem(
                                risk = "Regulatory / Platform Dependence Risk",
                                fix = "Never build an entire business that can be destroyed by an OpenAI API price change or a Google search algorithm tweak. Own the customer relationship, proprietary database, and domain."
                            )

                            RiskItem(
                                risk = "Key-Man & Healthcare Vulnerability",
                                fix = "Maintain comprehensive health insurance, term life insurance, and document every operational SOP so the business operates if you are hospitalized for 3 weeks."
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun OSCadenceCard(cadence: String, borderColor: androidx.compose.ui.graphics.Color, items: List<String>) {
    Card(
        colors = CardDefaults.cardColors(containerColor = ObsidianCard),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, OutlineBorder),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(text = cadence, color = borderColor, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.5.sp)
            Spacer(modifier = Modifier.height(8.dp))
            items.forEach { item ->
                Row(
                    modifier = Modifier.padding(vertical = 3.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Text(text = "• ", color = borderColor, fontWeight = FontWeight.Black)
                    Text(text = item, color = ChampagneText, fontSize = 12.sp, lineHeight = 16.sp)
                }
            }
        }
    }
}

@Composable
fun CapTableRule(ruleNum: String, title: String, text: String) {
    Column(modifier = Modifier.padding(vertical = 6.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = ruleNum, color = ImperialGold, fontSize = 10.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = title, color = ChampagneText, fontSize = 13.sp, fontWeight = FontWeight.Bold)
        }
        Text(text = text, color = ChampagneMuted, fontSize = 12.sp, lineHeight = 16.sp)
    }
}

@Composable
fun LegalPillar(title: String, desc: String) {
    Column(modifier = Modifier.padding(vertical = 6.dp)) {
        Text(text = title, color = EmeraldProfit, fontSize = 13.sp, fontWeight = FontWeight.Bold)
        Text(text = desc, color = ChampagneText, fontSize = 12.sp, lineHeight = 16.sp)
    }
}

@Composable
fun RiskItem(risk: String, fix: String) {
    Column(modifier = Modifier.padding(vertical = 6.dp)) {
        Text(text = "THREAT: $risk", color = CrimsonRisk, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        Text(text = "DEFENSE: $fix", color = ChampagneText, fontSize = 12.sp, lineHeight = 16.sp)
    }
}

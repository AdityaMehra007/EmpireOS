package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.repository.RoadmapRepository
import com.example.ui.theme.*

@Composable
fun RoadmapScreen(
    isUsdMode: Boolean,
    onToggleCurrency: () -> Unit
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("8 Empire Stages", "90-Day Execution", "First 10 Businesses", "50 Ways to $100M")

    // Interactive Checklist for 90-day plan
    val dayChecks = remember {
        mutableStateMapOf(
            1 to true, // Master n8n & Clay
            2 to false, // Build 3 Loom demos
            3 to false, // Set up Apollo & Smartlead
            4 to false, // Send 50 outbound messages/day
            5 to false, // Book first 5 pilot discovery calls
            6 to false, // Close first ₹25k-₹50k paid setup
            7 to false, // Register GST + LUT (0% export tax)
            8 to false, // Sign first $1,500/mo ongoing retainer
            9 to false, // Hire first Bangalore engineering intern
            10 to false // Cross ₹5 Lakhs/month recurring cash flow
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(ObsidianBg)
            .testTag("roadmap_screen"),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 96.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Header
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "BUILD ADITYA MEHRA'S BUSINESS EMPIRE",
                        color = ImperialGold,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "From Bangalore to Global Monopolies",
                        color = ChampagneText,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Button(
                    onClick = onToggleCurrency,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isUsdMode) SapphireTech else ImperialGold,
                        contentColor = ObsidianBg
                    ),
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 2.dp),
                    modifier = Modifier.height(32.dp)
                ) {
                    Text(
                        text = if (isUsdMode) "$ USD" else "₹ INR",
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp
                    )
                }
            }
        }

        // Navigation Tabs Row
        item {
            ScrollableTabRow(
                selectedTabIndex = selectedTab,
                containerColor = ObsidianCard,
                contentColor = ImperialGold,
                edgePadding = 0.dp,
                divider = { Divider(color = OutlineBorder) }
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = {
                            Text(
                                text = title,
                                fontSize = 12.sp,
                                fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal,
                                color = if (selectedTab == index) ImperialGold else ChampagneMuted
                            )
                        }
                    )
                }
            }
        }

        when (selectedTab) {
            0 -> {
                // TAB 0: 8 EMPIRE STAGES
                items(RoadmapRepository.stages, key = { it.stageNumber }) { stage ->
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = if (stage.stageNumber == 1) ObsidianSurface else ObsidianCard
                        ),
                        shape = RoundedCornerShape(14.dp),
                        border = BorderStroke(
                            width = if (stage.stageNumber == 1) 1.5.dp else 1.dp,
                            color = if (stage.stageNumber == 1) ImperialGold else OutlineBorder
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Surface(
                                        color = if (stage.stageNumber == 1) ImperialGold else ObsidianSurfaceVariant,
                                        shape = RoundedCornerShape(6.dp)
                                    ) {
                                        Text(
                                            text = "STAGE ${stage.stageNumber}",
                                            color = if (stage.stageNumber == 1) ObsidianBg else ChampagneText,
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.Black,
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = stage.title,
                                        color = ChampagneText,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }

                                Text(
                                    text = if (isUsdMode) stage.rangeUsd else stage.rangeInr,
                                    color = ImperialGold,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "Objective: ${stage.coreObjective}",
                                color = ChampagneText,
                                fontSize = 12.sp,
                                lineHeight = 16.sp
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = "Vehicle: ${stage.primaryBusinessModel}",
                                color = SapphireTech,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Divider(color = OutlineBorder.copy(alpha = 0.5f))

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = "STARTING CAPITAL & STRATEGY:",
                                color = ChampagneMuted,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "${stage.startingCapital} • ${stage.customerStrategy}",
                                color = ChampagneMuted,
                                fontSize = 11.sp
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = "LEGAL & TAX ARCHITECTURE:",
                                color = ChampagneMuted,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = stage.legalAndTaxStructure,
                                color = EmeraldProfit,
                                fontSize = 11.sp
                            )
                        }
                    }
                }
            }

            1 -> {
                // TAB 1: 90-DAY TACTICAL EXECUTION PLAN
                item {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                        shape = RoundedCornerShape(14.dp),
                        border = BorderStroke(1.5.dp, ImperialGold)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Schedule, contentDescription = "90-Day Plan", tint = ImperialGold)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "90-DAY LAUNCH SPRINT: MEHRA GLOBAL AI SYSTEMS",
                                    color = ImperialGold,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Black
                                )
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Goal: Go from ₹0 to ₹5 Lakhs/month ($5,300/mo) in high-margin international cash flow from your desk in Bangalore. Check off completed items:",
                                color = ChampagneMuted,
                                fontSize = 12.sp
                            )
                        }
                    }
                }

                item {
                    Text(
                        text = "PHASE 1: DAYS 1–15 (SKILLS & VERIFIABLE PROTOTYPES)",
                        color = SapphireTech,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                listOf(
                    1 to "Master n8n, Make, and Clay. Build 3 production workflows (Automated B2B Lead Enricher, 24/7 AI Voice/WhatsApp Qualifier, Invoice PDF Parser).",
                    2 to "Record 3 sharp 3-minute Loom video teardowns explaining the exact problem and showing the live system in action.",
                    3 to "Set up domain, Google Workspace, Apollo.io, and Smartlead with 3 secondary domains for cold outreach."
                ).forEach { (id, text) ->
                    item {
                        ExecutionCheckItem(
                            text = text,
                            isChecked = dayChecks[id] ?: false,
                            onToggle = { dayChecks[id] = !(dayChecks[id] ?: false) }
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "PHASE 2: DAYS 16–45 (OUTBOUND BLITZ & PILOTS)",
                        color = EmeraldProfit,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                listOf(
                    4 to "Send 50 personalized outreach touches daily (LinkedIn DMs + Cold Email) targeting US/UK/UAE B2B SaaS and Freight Forwarders.",
                    5 to "Offer a free '14-Day AI Workflow Audit & Deployment' pilot: zero upfront fee, pay ₹25,000 only after 5 qualified leads or 10 hours saved.",
                    6 to "Conduct 15 discovery calls, onboard 2 pilot clients, and successfully bank your first ₹50,000 to ₹1,00,000."
                ).forEach { (id, text) ->
                    item {
                        ExecutionCheckItem(
                            text = text,
                            isChecked = dayChecks[id] ?: false,
                            onToggle = { dayChecks[id] = !(dayChecks[id] ?: false) }
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "PHASE 3: DAYS 46–90 (RETAINERS, LEGAL ENTITY & SCALE)",
                        color = ImperialGold,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                listOf(
                    7 to "Register GST + file GST LUT (Letter of Undertaking) for 0% tax on service exports. Set up Skydo / Wise Business to collect USD with e-BRC.",
                    8 to "Convert pilot clients into $1,500–$2,000/month recurring maintenance and optimization retainers.",
                    9 to "Hire first part-time engineering intern from RVCE/BMS/PES Bangalore (₹18,000/mo) to manage client workflow maintenance.",
                    10 to "Hit 4 active retainers = ₹5.6 Lakhs/month recurring revenue at 80% net margin. Stage 1 COMPLETE."
                ).forEach { (id, text) ->
                    item {
                        ExecutionCheckItem(
                            text = text,
                            isChecked = dayChecks[id] ?: false,
                            onToggle = { dayChecks[id] = !(dayChecks[id] ?: false) }
                        )
                    }
                }
            }

            2 -> {
                // TAB 2: FIRST 10 BUSINESSES BLUEPRINT
                item {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                        shape = RoundedCornerShape(14.dp),
                        border = BorderStroke(1.5.dp, ImperialGold)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text(
                                text = "THE 10-BUSINESS SEQUENTIAL FLYWHEEL",
                                color = ImperialGold,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Black
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Do NOT launch all 10 simultaneously. Launch Business #1 first; let its cash flow and proprietary data fund #2, #3, and #4 over a 5-year timeline.",
                                color = ChampagneText,
                                fontSize = 12.sp
                            )
                        }
                    }
                }

                listOf(
                    Triple(
                        "#1 RECOMMENDED LAUNCH: Mehra Global AI Systems",
                        "B2B AI Revenue & Operations Agency | Capital: ₹5k | Target: ₹5L-₹15L/mo",
                        "Builds autonomous lead-generation, voice qualification, and invoice-processing agents for US/EU/UAE firms. Cash cow that funds software R&D."
                    ),
                    Triple(
                        "#2: TradeFlow AI",
                        "Vertical AI SaaS for Cross-Border Logistics | Funded by #1 Cash Flow",
                        "Vision-LLM document parser automating customs invoices, Bill of Lading, and EU CBAM carbon filings for global forwarders ($499-$2,499/mo)."
                    ),
                    Triple(
                        "#3: The Indo-Global Trade & AI Brief",
                        "B2B Executive Media & Newsletter | Capital: ₹10k | Zero-CAC Funnel",
                        "Twice-weekly deep intelligence on 'China+1' manufacturing and Indian GCC AI workflows. Monetized via $5k B2B sponsorships."
                    ),
                    Triple(
                        "#4: IndusSource B2B",
                        "Managed Cross-Border Marketplace for Precision Parts | Take Rate: 10%",
                        "Connects US/EU engineering buyers with vetted ISO-certified CNC & sheet-metal factories in Peenya, Pune, and Coimbatore."
                    ),
                    Triple(
                        "#5: ClearSettle FX",
                        "B2B Cross-Border Treasury & Export Invoicing Fintech",
                        "Helps Indian SaaS and merchant exporters book lowest-spread FX and automate RBI e-BRC/EDPMS compliance."
                    )
                ).forEach { (title, subtitle, desc) ->
                    item {
                        Card(
                            colors = CardDefaults.cardColors(containerColor = ObsidianCard),
                            shape = RoundedCornerShape(12.dp),
                            border = BorderStroke(1.dp, OutlineBorder)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(text = title, color = ImperialGold, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                                Text(text = subtitle, color = SapphireTech, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = desc, color = ChampagneMuted, fontSize = 12.sp, lineHeight = 16.sp)
                            }
                        }
                    }
                }
            }

            3 -> {
                // TAB 3: 50 WAYS TO $100M
                items(RoadmapRepository.topBusinessModels, key = { it.id }) { model ->
                    Card(
                        colors = CardDefaults.cardColors(containerColor = ObsidianCard),
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.dp, OutlineBorder)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "#${model.id} ${model.name}",
                                    color = ChampagneText,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.weight(1f)
                                )
                                Surface(
                                    color = if (model.rankingPriority == 1) EmeraldProfit.copy(alpha = 0.2f) else SapphireTech.copy(alpha = 0.2f),
                                    shape = RoundedCornerShape(4.dp)
                                ) {
                                    Text(
                                        text = if (model.rankingPriority == 1) "PRIORITY 1" else "PRIORITY ${model.rankingPriority}",
                                        color = if (model.rankingPriority == 1) EmeraldProfit else SapphireTech,
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = model.whoPaysAndWhy, color = ChampagneMuted, fontSize = 11.sp)

                            Spacer(modifier = Modifier.height(6.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = "Startup: ${model.startupCapitalInr}", color = ChampagneText, fontSize = 11.sp)
                                Text(text = "Gross Margin: ${model.grossMarginPct}", color = ImperialGold, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                Text(text = "Scale: ${model.scalabilityScore}/10", color = SapphireTech, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            }

                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Bangalore Entry: ${model.beginnerEntryBangalore}",
                                color = EmeraldProfit,
                                fontSize = 11.sp,
                                lineHeight = 15.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ExecutionCheckItem(
    text: String,
    isChecked: Boolean,
    onToggle: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = if (isChecked) ObsidianSurfaceVariant else ObsidianCard
        ),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, if (isChecked) ImperialGold else OutlineBorder),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onToggle() }
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = { onToggle() },
                colors = CheckboxDefaults.colors(
                    checkedColor = ImperialGold,
                    checkmarkColor = ObsidianBg,
                    uncheckedColor = ChampagneMuted
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                color = if (isChecked) ImperialGoldLight else ChampagneText,
                fontSize = 12.sp,
                lineHeight = 16.sp
            )
        }
    }
}

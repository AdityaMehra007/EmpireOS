package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import com.example.data.model.BillionaireTitan
import com.example.data.repository.TitansRepository
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TitansScreen(
    isUsdMode: Boolean,
    onToggleCurrency: () -> Unit
) {
    val usdInrRate = 94.42
    val allTitans = remember { TitansRepository.titans }
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("All") }
    var expandedTitanId by remember { mutableStateOf<String?>(null) }

    val categories = listOf("All", "Tech & AI", "India Champions", "Global Moguls", "Historical")

    val filteredTitans = remember(searchQuery, selectedCategory) {
        allTitans.filter { titan ->
            val matchesCategory = when (selectedCategory) {
                "All" -> true
                "Tech & AI" -> titan.sector.contains("AI") || titan.sector.contains("Tech") || titan.sector.contains("Software") || titan.sector.contains("Semiconductor")
                "India Champions" -> titan.country == "India"
                "Global Moguls" -> !titan.isHistorical && titan.country != "India" && !titan.sector.contains("Software")
                "Historical" -> titan.isHistorical
                else -> true
            }
            val matchesSearch = titan.name.contains(searchQuery, ignoreCase = true) ||
                    titan.primaryCompany.contains(searchQuery, ignoreCase = true) ||
                    titan.sector.contains(searchQuery, ignoreCase = true)
            matchesCategory && matchesSearch
        }
    }

    fun formatNetWorth(worthUsd: Double): String {
        return if (isUsdMode) {
            "$${String.format("%.1f", worthUsd)} Billion"
        } else {
            val inrCr = worthUsd * (usdInrRate * 100.0) // 1B USD = ~₹9,442 Cr
            if (inrCr >= 100_000.0) {
                "₹${String.format("%.2f", inrCr / 100_000.0)} Lakh Crore"
            } else {
                "₹${String.format("%,.0f", inrCr)} Crore"
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(ObsidianBg)
            .testTag("titans_screen"),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 96.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
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
                        text = "GLOBAL BILLIONAIRE & TITAN INTELLIGENCE",
                        color = ImperialGold,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "3,428 Fortunes Deconstructed",
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

        // Search Bar
        item {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Search by name, company, or sector...", color = ChampagneMuted, fontSize = 13.sp) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search", tint = ImperialGold) },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { searchQuery = "" }) {
                            Icon(Icons.Default.Close, contentDescription = "Clear", tint = ChampagneMuted)
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("titans_search_input"),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = ImperialGold,
                    unfocusedBorderColor = OutlineBorder,
                    focusedTextColor = ChampagneText,
                    unfocusedTextColor = ChampagneText,
                    focusedContainerColor = ObsidianCard,
                    unfocusedContainerColor = ObsidianCard
                ),
                singleLine = true
            )
        }

        // Category Filter Chips
        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(categories) { cat ->
                    val isSelected = selectedCategory == cat
                    FilterChip(
                        selected = isSelected,
                        onClick = { selectedCategory = cat },
                        label = { Text(cat, fontSize = 12.sp, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = ImperialGold,
                            selectedLabelColor = ObsidianBg,
                            containerColor = ObsidianCard,
                            labelColor = ChampagneText
                        ),
                        border = BorderStroke(1.dp, if (isSelected) ImperialGold else OutlineBorder),
                        shape = RoundedCornerShape(8.dp)
                    )
                }
            }
        }

        // Titan Cards List
        items(filteredTitans, key = { it.id }) { titan ->
            val isExpanded = expandedTitanId == titan.id

            Card(
                colors = CardDefaults.cardColors(containerColor = ObsidianCard),
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(
                    width = if (isExpanded) 1.5.dp else 1.dp,
                    color = if (isExpanded) ImperialGold else OutlineBorder
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        expandedTitanId = if (isExpanded) null else titan.id
                    }
                    .testTag("titan_card_${titan.id}")
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = titan.name,
                                    color = ChampagneText,
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                if (titan.isHistorical) {
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Surface(
                                        color = SapphireTech.copy(alpha = 0.2f),
                                        shape = RoundedCornerShape(4.dp)
                                    ) {
                                        Text(
                                            text = "HISTORICAL",
                                            color = SapphireTech,
                                            fontSize = 9.sp,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                                        )
                                    }
                                }
                            }
                            Text(
                                text = "${titan.primaryCompany} • ${titan.country}",
                                color = ChampagneMuted,
                                fontSize = 12.sp
                            )
                        }

                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = formatNetWorth(titan.netWorthUsd),
                                color = ImperialGold,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Black
                            )
                            Text(
                                text = titan.sector,
                                color = ChampagneMuted,
                                fontSize = 11.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Quick Stats Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(ObsidianSurface, RoundedCornerShape(8.dp))
                            .padding(horizontal = 10.dp, vertical = 6.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(text = "First Million", color = ChampagneMuted, fontSize = 10.sp)
                            Text(text = "Age ${titan.firstMillionAge}", color = ChampagneText, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                        Column {
                            Text(text = "First Billion", color = ChampagneMuted, fontSize = 10.sp)
                            Text(text = "Age ${titan.firstBillionAge}", color = EmeraldProfit, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                        Column {
                            Text(text = "Ownership", color = ChampagneMuted, fontSize = 10.sp)
                            Text(text = titan.ownershipStake.take(18) + "..", color = SapphireTech, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Prominent Highlight: Lesson for Aditya Mehra
                    Surface(
                        color = ImperialGoldDark.copy(alpha = 0.15f),
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(1.dp, ImperialGold.copy(alpha = 0.3f)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(10.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Icon(
                                imageVector = Icons.Default.Lightbulb,
                                contentDescription = "Lesson",
                                tint = ImperialGold,
                                modifier = Modifier
                                    .size(18.dp)
                                    .padding(top = 1.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(
                                    text = "KEY LESSON FOR ADITYA MEHRA (BANGALORE):",
                                    color = ImperialGold,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = titan.lessonForAditya,
                                    color = ChampagneText,
                                    fontSize = 12.sp,
                                    lineHeight = 16.sp
                                )
                            }
                        }
                    }

                    // Expanded In-Depth Teardown
                    AnimatedVisibility(visible = isExpanded) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 12.dp)
                        ) {
                            Divider(color = OutlineBorder)
                            Spacer(modifier = Modifier.height(8.dp))

                            TitanDetailRow(label = "Starting Capital", value = titan.startingCapital)
                            TitanDetailRow(label = "Wealth Mechanism", value = titan.sourceOfWealth)
                            TitanDetailRow(label = "Corporate Scale", value = "${titan.corporateRevenue} | Net Profit: ${titan.corporateNetIncome}")
                            TitanDetailRow(label = "Core Strategy", value = titan.coreStrategy)
                            TitanDetailRow(label = "Management Style", value = titan.managementPhilosophy)
                            TitanDetailRow(label = "Major Failure/Crisis", value = titan.majorFailure)
                            TitanDetailRow(label = "Daily Routine & Lifestyle", value = titan.dailyRoutineAndLifestyle)
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = if (isExpanded) "Tap to collapse ▲" else "Tap for complete forensic teardown ▼",
                            color = ChampagneMuted,
                            fontSize = 10.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TitanDetailRow(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(text = label.uppercase(), color = ChampagneMuted, fontSize = 10.sp, fontWeight = FontWeight.Bold)
        Text(text = value, color = ChampagneText, fontSize = 12.sp, lineHeight = 16.sp)
    }
}

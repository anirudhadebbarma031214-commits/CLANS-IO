package com.anistudio.clansio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Forum
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Ink = Color(0xFF0A0D14)
private val Panel = Color(0xFF151B27)
private val Lime = Color(0xFFB8F45B)
private val Muted = Color(0xFF9AA6B8)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { ClansApp() }
    }
}

@Composable
private fun ClansApp() {
    var selectedTab by remember { mutableIntStateOf(0) }
    MaterialTheme {
        Scaffold(
            containerColor = Ink,
            bottomBar = {
                NavigationBar(containerColor = Panel, contentColor = Color.White) {
                    val tabs = listOf("Home", "Clans", "Matches", "Chat")
                    val icons = listOf(Icons.Default.Home, Icons.Default.Groups, Icons.Default.EmojiEvents, Icons.Default.Forum)
                    tabs.forEachIndexed { index, label ->
                        NavigationBarItem(
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            icon = { Icon(icons[index], contentDescription = label) },
                            label = { Text(label, fontSize = 10.sp) }
                        )
                    }
                }
            }
        ) { padding ->
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(padding).padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                item { Header() }
                item {
                    Card(
                        shape = RoundedCornerShape(26.dp),
                        colors = CardDefaults.cardColors(containerColor = Panel),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Brush.linearGradient(listOf(Color(0xFF283B31), Color(0xFF18212B), Panel)))
                                .padding(22.dp)
                        ) {
                            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                                Text("YOUR SQUAD. YOUR LEGACY.", color = Lime, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.6.sp)
                                Text("Play together.\nRise together.", color = Color.White, fontSize = 30.sp, lineHeight = 35.sp, fontWeight = FontWeight.ExtraBold)
                                Text("Your gaming community starts here.", color = Muted, fontSize = 13.sp)
                                Button(
                                    onClick = { selectedTab = 1 },
                                    colors = ButtonDefaults.buttonColors(containerColor = Lime, contentColor = Ink),
                                    shape = RoundedCornerShape(14.dp)
                                ) {
                                    Icon(Icons.Default.Groups, contentDescription = null, modifier = Modifier.size(18.dp))
                                    Spacer(Modifier.size(8.dp))
                                    Text("Explore clans", fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }
                }
                item { SectionTitle("YOUR OVERVIEW", "SEASON 01") }
                item {
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        StatCard("CLAN MEMBERS", "24", "+3 this week", Modifier.weight(1f))
                        StatCard("MATCH WINS", "18", "72% win rate", Modifier.weight(1f))
                    }
                }
                item { SectionTitle("RECENT ACTIVITY", "VIEW ALL") }
                item {
                    ActivityCard(
                        icon = Icons.Default.EmojiEvents,
                        title = "Weekend Scrim",
                        subtitle = "Your squad placed #2 in the lobby",
                        tag = "COMPLETED"
                    )
                }
                item {
                    ActivityCard(
                        icon = Icons.Default.PersonAdd,
                        title = "Find your next teammate",
                        subtitle = "Build your roster with players who match your style",
                        tag = "SQUAD UP"
                    )
                }
                item {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF202B1A)),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Icon(Icons.Default.SportsEsports, contentDescription = null, tint = Lime, modifier = Modifier.size(28.dp))
                            Column(Modifier.weight(1f)) {
                                Text("Ready for the next match?", color = Color.White, fontWeight = FontWeight.Bold)
                                Text("Invite your crew and make a plan.", color = Muted, fontSize = 12.sp)
                            }
                        }
                    }
                }
                item { Spacer(Modifier.height(8.dp)) }
            }
        }
    }
}

@Composable
private fun Header() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 22.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Box(
                modifier = Modifier.size(42.dp).background(Lime, RoundedCornerShape(14.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Groups, contentDescription = null, tint = Ink, modifier = Modifier.size(25.dp))
            }
            Column {
                Text("CLANS.IO", color = Color.White, fontSize = 19.sp, fontWeight = FontWeight.Black, letterSpacing = 1.4.sp)
                Text("GAMING COMMUNITY", color = Muted, fontSize = 9.sp, letterSpacing = 1.8.sp)
            }
        }
        Box(
            modifier = Modifier.size(42.dp).background(Panel, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text("A", color = Lime, fontWeight = FontWeight.Bold, fontSize = 17.sp)
        }
    }
}

@Composable
private fun SectionTitle(title: String, action: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(title, color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.2.sp)
        Text(action, color = Lime, fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.8.sp)
    }
}

@Composable
private fun StatCard(title: String, value: String, hint: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Panel)
    ) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(title, color = Muted, fontSize = 9.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.6.sp)
            Text(value, color = Color.White, fontSize = 30.sp, fontWeight = FontWeight.ExtraBold)
            Text(hint, color = Lime, fontSize = 10.sp)
        }
    }
}

@Composable
private fun ActivityCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    tag: String
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Panel),
        shape = RoundedCornerShape(18.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier.size(44.dp).background(Color(0xFF273122), RoundedCornerShape(14.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = Lime)
            }
            Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text(subtitle, color = Muted, fontSize = 11.sp, lineHeight = 15.sp)
            }
            Text(tag, color = Lime, fontSize = 8.sp, fontWeight = FontWeight.Bold)
        }
    }
}

lucide.createIcons();
const toggle = document.querySelector(".menu-toggle");
if (toggle) toggle.addEventListener("click", () => document.body.classList.toggle("sidebar-open"));
const defaults={responsive:true,maintainAspectRatio:false,plugins:{legend:{labels:{color:"#6b7280",font:{family:"Inter",weight:700}}}},scales:{x:{grid:{display:false},ticks:{color:"#6b7280"}},y:{grid:{color:"#eef0f4"},ticks:{color:"#6b7280"}}}};
function draw(id,config){const el=document.getElementById(id);if(el)new Chart(el,config)}
draw("salesTrend",{type:"line",data:{labels:["Mon","Tue","Wed","Thu","Fri","Sat","Sun"],datasets:[{label:"Tickets",data:[620,710,680,840,960,1280,1185],borderColor:"#dc2626",backgroundColor:"rgba(220,38,38,.12)",tension:.38,fill:true,pointRadius:4}]},options:defaults});
draw("revenueByFilm",{type:"bar",data:{labels:["Dune","Oppenheimer","Parasite","Budapest"],datasets:[{label:"DH",data:[98400,74200,52100,38600],backgroundColor:["#dc2626","#f59e0b","#6366f1","#111827"],borderRadius:10}]},options:defaults});
draw("occupancyByScreening",{type:"doughnut",data:{labels:["Salle 1","Salle 3","VIP","Salle 2"],datasets:[{data:[92,78,64,48],backgroundColor:["#dc2626","#f59e0b","#6366f1","#111827"],borderWidth:0}]},options:{responsive:true,maintainAspectRatio:false,plugins:{legend:{position:"bottom"}}}});
draw("statsRevenue",{type:"bar",data:{labels:["Dune","Oppenheimer","Parasite","Budapest"],datasets:[{label:"Revenue",data:[98400,74200,52100,38600],backgroundColor:"#dc2626",borderRadius:10}]},options:defaults});
draw("monthlyTickets",{type:"line",data:{labels:["Jan","Feb","Mar","Apr","May","Jun"],datasets:[{label:"Tickets",data:[9200,10800,12100,11600,14200,18928],borderColor:"#6366f1",backgroundColor:"rgba(99,102,241,.12)",fill:true,tension:.38}]},options:defaults});
draw("genreDistribution",{type:"pie",data:{labels:["Sci-fi","Drama","Comedy","Biopic"],datasets:[{data:[36,28,18,18],backgroundColor:["#dc2626","#f59e0b","#6366f1","#111827"],borderWidth:0}]},options:{responsive:true,maintainAspectRatio:false,plugins:{legend:{position:"bottom"}}}});

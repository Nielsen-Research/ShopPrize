jsPlumb.ready(function() {

var link12 = jsPlumb.getInstance();

var common12 = {
  anchors:[ "Right", "Left" ],
  endpoints:["Blank", "Blank" ]
};
var common23 = {
  anchors:[ "Top", "Left"],
  endpoints:["Blank", "Blank" ]
};
var common25 = {
  anchors:[ "BottomCenter", "Left" ],
  endpoints:["Blank", "Blank" ]
};
var common34 = {
  anchors:[ "Right", "Left" ],
  endpoints:["Blank", "Blank" ]
};
var common56 = {
  anchors:[ "Right", "Left" ],
  endpoints:["Blank", "Blank" ]
};
var common67 = {
  anchors:[ "Right", "BottomCenter" ],
  endpoints:["Blank", "Blank" ]
};
var common47 = {
  anchors:[ "Right", "Top" ],
  endpoints:["Blank", "Blank" ]
};
var common78 = {
  anchors:[ [ 0.7, 0, 0, -1], [ 0.5, 0, 0, -1] ],
  endpoints:["Blank", "Blank" ]
};
var common87 = {
  anchors:[ "BottomCenter", [0.7, 1, 0, 1, 0, 0] ],
  endpoints:["Blank", "Blank" ]
};
var common89 = {
  anchors:[ "Right", "Left" ],
  endpoints:["Blank", "Blank" ]
};
link12.connect({
  source:"node1", 
  target:"node2",
 connector:[ "Straight", {gap:10}],

 overlays: [
        [ "Arrow", { foldback:0.2 }],
		],
	
},common12);
link12.connect({
  source:"node2", 
  target:"node3",
  connector:[ "Flowchart", { cornerRadius:30,gap:10}],
  overlays: [
        [ "Arrow", { foldback:0.2 }],
		],
  
},common23);
link12.connect({
  source:"node2", 
  target:"node5",
 connector:[ "Flowchart", { cornerRadius:30,gap:10}],
  overlays: [
        [ "Arrow", { foldback:0.2 }],
		],
		 
},common25);
link12.connect({
  source:"node3", 
  target:"node4",
  connector:[ "Straight", {gap:10}],
  overlays: [
        [ "Arrow", { foldback:0.2 }],
		],
},common34);

link12.connect({
  source:"node5", 
  target:"node6",
 connector:[ "Straight", {gap:10}],
  overlays: [
        [ "Arrow", { foldback:0.2 }],
		],
},common56);

link12.connect({
  source:"node6", 
  target:"node7",
  connector:[ "Flowchart", { cornerRadius:30,gap:10}],
  overlays: [
        [ "Arrow", { foldback:0.2 }],
		],
},common67);
link12.connect({
  source:"node4", 
  target:"node7",
  connector:[ "Flowchart", { cornerRadius:30,gap:10}],
  overlays: [
        [ "Arrow", { foldback:0.2 }],
		],
},common47);

link12.connect({
  source:"node7", 
  target:"node8",
  connector:[ "Flowchart", { cornerRadius:40,gap:10}],
  overlays: [
        [ "Arrow", { foldback:0.2 }],
		],
},common78);

link12.connect({
  source:"node8", 
  target:"node7",
  connector:[ "Flowchart", { cornerRadius:40,gap:10}],
  overlays: [
        [ "Arrow", { foldback:0.2 }],
		],
},common87);
link12.connect({
  source:"node8", 
  target:"node9",
  connector:[ "Straight", {gap:10}],
  overlays: [
        [ "Arrow", { foldback:0.2 }],
		],
},common89);

}, true);
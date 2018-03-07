/*
 * geekjs.js 0.1
 *
 * Copyright (c) 2008 Mark Nichols (zanshin.net)
 *
 * 11 november 2008
 */
	$(document).ready(function(){

		// enable sortable on all columns
		// this will add drag-and-drop functionality as well
		$("#left").sortable({
			connectWith: ["#leftCenter","#rightCenter","#right"]
		});
		$("#leftCenter").sortable({
			connectWith: ["#left","rightCenter","#right"]
		});
		$("#rightCenter").sortable({
			connectWith: ["#left","#leftCenter","#right"]
		});
		$("#right").sortable({
			connectWith: ["#left","#leftCenter","rightCenter"]
		});
	});
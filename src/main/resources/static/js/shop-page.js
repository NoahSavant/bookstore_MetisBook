(function($) {
    "use strict";

	$(".page-item").click(function() {
		pageChange(this.id);
	});
	
	function pageChange(pageId) {
		var controller = document.getElementById("cur_page");
		var cur_page = parseInt(controller.value);
		var last_page = cur_page;
		if(pageId == 'p-before'){
			if(cur_page == 1) {
				return;
			}
			cur_page = cur_page - 1;
		}else if(pageId == "p-after") {
			if(cur_page == Math.ceil(document.getElementById("size").value/6)) {
				return;
			}
			cur_page = cur_page + 1;
		}else {
			cur_page = parseInt(pageId.split("-")[1]);
		}
		if(last_page == cur_page) {
			return;
		}
		controller.value = cur_page;
		var url = window.location.href.split("=")[0] + "=" + cur_page.toString();
		history.pushState({}, "", url);
		displayList(last_page);
	}
	
	function createPageList() {
		var size = document.getElementById("size").value;
		var maxPage = Math.ceil(size/6);
		var pagination = document.getElementById("pagination");
		var page_item = document.getElementById("p-after");
		for(let i = 1; i <= maxPage; i++) {
			var newPage = page_item.cloneNode(true);
			newPage.setAttribute('id', 'p-' + i.toString());
			newPage.firstChild.innerHTML = i;
			newPage.addEventListener("click", function(){pageChange(this.id)});
			pagination.insertBefore(newPage, page_item);
		}
		displayList(0);
	}
	
	function displayList(last_page) {
		var cur_page = document.getElementById("cur_page").value;
		var size = document.getElementById("size").value;
		var maxPage = Math.ceil(size/6);	
		
		
		for(let i = 1; i <= maxPage; i++) {
			var element = document.getElementById('p-' + i.toString());
			if(Math.abs(cur_page -i) <= 1 || i == maxPage || i == 1) {
				element.style.display = "block";
			} else {
				element.style.display = "none";
			}
		}
		
		document.getElementById('p-' + cur_page).firstChild.innerHTML = '<div style="color:  #d33b33">'+ cur_page +'</div>'; 
		
		if(last_page != 0) {
			let last = document.getElementById('p-' + last_page);
			if(last != null) {
				last.firstChild.innerHTML = last_page;
			}
			let start =  (last_page - 1) * 6;
			let end = Math.min(size, last_page * 6);
			for(let i = start; i < end; i++) {
				document.getElementById('short' + i.toString()).style.display = "none";
				document.getElementById('long' + i.toString()).style.display = "none";
			}
		}
		let start =  (cur_page - 1) * 6;
		let end = Math.min(size, cur_page * 6);
		for(let i = start; i < end; i++) {
			document.getElementById('short' + i.toString()).style.display = "block";
			document.getElementById('long' + i.toString()).style.display = "block";
		}
	}
	
	$(document).ready(function() {
		createPageList();
	});

	/* ..............................................
	   Slider Range
	   ................................................. */

	$(function() {
		var maxPrice = document.getElementById("maxPrice").value;
		$("#slider-range").slider({
			range: true,
			min: 0,
			max: maxPrice,
			values: [document.getElementById("min").value, document.getElementById("max").value],
			slide: function(event, ui) {
				$("#amount").val(ui.values[0] + " VNĐ - " + ui.values[1] + " VNĐ");
				document.getElementById("min").value = ui.values[0];
				document.getElementById("max").value = ui.values[1];
			}
		});
		$("#amount").val($("#slider-range").slider("values", 0) +
			" VNĐ - " + $("#slider-range").slider("values", 1) + " VNĐ");
		
	});
}(jQuery));


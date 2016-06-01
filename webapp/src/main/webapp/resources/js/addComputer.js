'use strict';

var body = document.querySelector("body");
body.addEventListener("load", checkDateOnLoad, false);


// Check if name length is > 2
function checkName() {
	var input = document.querySelector("#computerName");
	if (input.value.length < 4) {
		input.parentElement.classList.remove("has-success");
		input.parentElement.classList.add("has-error");
	} else {
		
		input.parentElement.classList.remove("has-error");
		input.parentElement.classList.add("has-success");
	}
};

function isDateValid(str) {
	var re = /^\d{4}-\d{2}-\d{2}$/;
	if (re.test(str)) {
		var before = new Date("1970-01-01");
		var after = new Date("2037-12-31");
		var current = new Date(str);
		return before <= current && current <= after;
	}
	return false;
}

function checkDate(id) {
	var id = "#" + id;

	var input = document.querySelector(id);
	var value = input.value;
	if (value != ""
			&& (isNaN(Date.parse(value)) || !isDateValid(value))) {
		input.parentNode.classList.remove("has-success");
		input.parentNode.classList.add("has-error");
	} else {
		console.log(input.parentElement.nodeName);
		input.parentNode.classList.remove("has-error");
		input.parentNode.classList.add("has-success");
	}
}

function checkDateOnLoad(){
	console.log("in")
	checkDate("introduced");
	checkDate("discontinued");
}
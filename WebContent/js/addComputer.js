'use strict';

function $(string) {
	return document.querySelector(string);
}

function checkName() {
	alert();
}

// Check if name length is > 2
function checkName() {
	var input = document.querySelector("#computerName");
	if (input.value.length < 4) {
		input.parentNode.classList.remove("has-success");
		input.parentNode.classList.add("has-error");
	} else {
		input.parentNode.classList.remove("has-error");
		input.parentNode.classList.add("has-success");
	}
};

function isDateValid(str) {
	var before = new Date("1970-01-01");
	var after = new Date("2037-12-31");
	var current = new Date(str);
	return before <= current && current <= after;
}

function checkDate(event) {
	var target = event.target || event.srcElement;
	var id = "#" + target.id;

	var input = document.querySelector(id);
	var introduced = input.value;
	if (introduced != ""
			&& (isNaN(Date.parse(introduced)) || !isDateValid(introduced))) {
		input.parentNode.classList.remove("has-success");
		input.parentNode.classList.add("has-error");
		alert();
	} else {
		input.parentNode.classList.remove("has-error");
		input.parentNode.classList.add("has-success");
	}

}
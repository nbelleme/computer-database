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
	} else {
		input.parentNode.classList.remove("has-error");
		input.parentNode.classList.add("has-success");
	}

}

function checkSubmit(event) {
	event.preventDefault();
	var form = event.target || event.srcElement;
	var introduced = document.querySelector("#introduced").value;
	var discontinued = document.querySelector("#discontinued").value;
	var isValidIntroduced = false;
	var isValidDiscontinued = false;
	if (!(introduced != "" && (isNaN(Date.parse(introduced)) || !isDateValid(introduced)))) {
		isValidIntroduced = true;
	}

	if (!(discontinued != "" && (isNaN(Date.parse(discontinued)) || !isDateValid(discontinued)))) {
		isValidDiscontinued = true;
	}

	if ((introduced == "" || isValidIntroduced)
			&& (discontinued == "" || isValidDiscontinued)) {
		document.querySelector("#introduced").value = dateToTimeStamp(introduced);
		document.querySelector("#discontinued").value = dateToTimeStamp(discontinued);
		
	} else {
		console.log("introduced : " + introduced);
		console.log("isValidIntroduced : " + isValidIntroduced);
		console.log("discontinued : " + discontinued );
		console.log("isValidDiscontinued : " + isValidDiscontinued);
		console.log("!ok");
	}

	 setTimeout(function(){
		 form.submit();
	 }, 000);
}

function dateToTimeStamp(str) {
	return new Date(str).getTime();
}
/* ========================================== 
scrollTop() >= 300
Should be equal the the height of the header
========================================== */

window.onscroll = function (el) {
	var nav = $('nav');
	if (window.pageYOffset >= 240) {
		// we have scrolled down the page
		nav.addClass('fixed-header');
		nav.removeClass('translucent');
		nav.addClass('opaque');

		$('#site-title').removeClass('invisible');

	}
	else {
		// we are at the top of the page
		nav.removeClass('fixed-header');
		nav.addClass('translucent');
		nav.removeClass('opaque');

		$('#site-title').addClass('invisible');

	}
};

$('#login').click(function () {
	var showThis = this.dataset.modal;
	showModal(showThis);
});

$('.js-close-modal').click(function () {
	var closeThis = this.dataset.modal;
	hideModal(closeThis);
});

function showModal(modalName) {
	$('#' + modalName).addClass('active');
}

function hideModal(modalName) {
	$('#' + modalName).removeClass('active');
}

function authenticate() {
	validate("/auth/validateLogin", "login_form", "login_form_validate", "/auth/loggedIn");
}


// Call the validation route given in validatorPath to validate the form in formName.
// If validation fails, update containerDiv through AJAX.
// If it succeeds, redirect to the given route
function validate(validatorPath, formName, containerDiv, redirectPath) {
	var serializedData = $('#' + formName).serialize();
	console.log(serializedData);
	// first, post to validator
	var error = false
	$.ajax({
		url: validatorPath,
		method: 'post',
		data: serializedData,
		success: function (response, statusText, xhr) {
			console.log("response: " + response);
			console.log("statusText: " + statusText);
			console.log("xhr: " + xhr);
			if(typeof response=="object") {
				console.log("It's probably JSON!");
				// var errorMap = JSON.parse(response);
				// console.log(JSON.stringify(errorMap));

				console.log("response['errors'][0]" + response['errors'][0]);

			} else {
				// if valid, move on to next action
				window.location.href = redirectPath
			}


			if (!response) {

			} else {
				// if in error, form should be re-rendered
				$('#' + containerDiv).html(response);
			}
		}
	});
}

// Call validation by providing an object to use named parameters
function validateAndRedirect(params) {
	validate(params.validator, params.form, params.div, params.success);
}
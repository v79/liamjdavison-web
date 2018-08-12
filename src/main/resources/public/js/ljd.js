/* ========================================== 
scrollTop() >= 300
Should be equal the the height of the header
========================================== */

/** Primary layout stuff **/
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

/** Primary navigation menu **/
$('#login').click(function () {
	var showThis = this.dataset.modal;
	showModal(showThis);
});

function authenticate() {
	validate("/auth/validateLogin", "login_form", "login_form_validate", "/");
}

/** Page creation and editing menu **/
$('#editnav_newPage').click(function () {
	var showThis = this.dataset.modal;
	$.ajax({
		url: "/edit/getPageTemplates",
		method: 'get'
	});
	showModal(showThis)
});

function createPage() {
	validate("/edit/validateCreatePage","new_page_form","new_page_form_validate",null);
}

/** Modal functions **/
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

/** Form validation and submission **/
// Call the validation route given in validatorPath to validate the form in formName.
// If validation fails, update containerDiv through AJAX.
// If it succeeds, redirect to the given route
function validate(validatorPath, formName, containerDiv , redirectPath) {
	var serializedData = $('#' + formName).serialize();
	console.log(serializedData);
	// first, post to validator
	var error = false;
	$.ajax({
		url: validatorPath,
		method: 'post',
		data: serializedData,
		success: function (response, statusText, xhr) {
			if(typeof response=="object") {

				for(let e of response['errors']) {
					let field = e['field'];
					let message = e['message'];
					console.log("setting " + field  + " -> " + message);
					$('#' + field).get(0).setCustomValidity(message);
					$('#' + field + '~ p').html(message);
				}

			} else {
				// if valid, move on to next action
				window.location.href = redirectPath
			}
		}
	});
}

// Call validation by providing an object to use named parameters
function validateAndRedirect(params) {
	validate(params.validator, params.form, params.div, params.success);
}
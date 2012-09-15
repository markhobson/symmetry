/* kozo html javascript */

function doGet(url)
{
	document.location = url;
}

function doPost(url)
{
	var form = document.forms[0];
	form.action = url;
	form.submit();
}
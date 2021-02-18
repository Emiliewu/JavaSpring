function checkDate() {

  var startDate = new Date(document.getElementById('expirationDate').value);
  var today = new Date();
  if (startDate.getTime() > today.getTime()) {
    alert("The expiration date must be at least tomorrow!");
  }  
}
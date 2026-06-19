window.onload = function () {

    let customerId = localStorage.getItem("customerId");

    fetch("/api/customers/" + customerId, {
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        }
    })
    .then(res => res.json())
    .then(data => {

        document.getElementById("username").innerText = data.username;
        document.getElementById("fullName").innerText = data.fullName;
        document.getElementById("email").innerText = data.email;
        document.getElementById("phoneNumber").innerText = data.phoneNumber;
        document.getElementById("idProof").innerText = data.idProof;
        document.getElementById("status").innerText = data.status;

    });
};
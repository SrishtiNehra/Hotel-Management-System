let customerId = localStorage.getItem("customerId");

window.onload = function () {

    fetch("/api/customers/" + customerId, {
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        }
    })
    .then(res => res.json())
    .then(data => {

        document.getElementById("username").value = data.username;
        document.getElementById("fullName").value = data.fullName;
        document.getElementById("email").value = data.email;
        document.getElementById("phoneNumber").value = data.phoneNumber;
        document.getElementById("idProof").value = data.idProof;

    });
};

function updateProfile() {

    let updatedCustomer = {
        username: document.getElementById("username").value,
        fullName: document.getElementById("fullName").value,
        email: document.getElementById("email").value,
        phoneNumber: document.getElementById("phoneNumber").value,
        idProof: document.getElementById("idProof").value
    };

    fetch("/api/customer/" + customerId, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + localStorage.getItem("token")
        },
        body: JSON.stringify(updatedCustomer)
    })
    .then(res => res.json())
    .then(() => {
        alert("Profile updated successfully!");
        window.location.href = "/customer/profile";
    });
}
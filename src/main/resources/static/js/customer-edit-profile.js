let customerId = localStorage.getItem("customerId");

window.onload = function () {

    fetch("/api/customers/" + customerId, {
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        }
    })
    .then(res => res.json())
    .then(data => {

        const username = document.getElementById("username");
        const fullName = document.getElementById("fullName");
        const email = document.getElementById("email");
        const phoneNumber = document.getElementById("phoneNumber");
        const idProof = document.getElementById("idProof");

        if (username) username.value = data.username;
        if (fullName) fullName.value = data.fullName;
        if (email) email.value = data.email;
        if (phoneNumber) phoneNumber.value = data.phoneNumber;
        if (idProof) idProof.value = data.idProof; // remove if deleted from backend
    });
};

function updateProfile() {

    const username = document.getElementById("username");
    const fullName = document.getElementById("fullName");
    const email = document.getElementById("email");
    const phoneNumber = document.getElementById("phoneNumber");

    if (!username || !fullName || !email || !phoneNumber) {
        alert("Form not loaded properly");
        return;
    }

    let updatedCustomer = {
        username: username.value,
        fullName: fullName.value,
        email: email.value,
        phoneNumber: phoneNumber.value
    };

    fetch("/api/customers/" + customerId, {
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
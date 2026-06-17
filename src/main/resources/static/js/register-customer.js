function registerCustomer() {

    const data = {
        username: document.getElementById("username").value,
        password: document.getElementById("password").value,
        fullName: document.getElementById("fullName").value,
        email: document.getElementById("email").value,
        phoneNumber: document.getElementById("phoneNumber").value,
        role: "CUSTOMER",
        status: "ACTIVE"
    };

    fetch("/api/auth/register/customer", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
    .then(res => res.text())
    .then(msg => {
        showToast(msg);
        window.location.href = "/auth/login";
    });
}
function registerAdmin() {

    const data = {
        username: document.getElementById("username").value,
        password: document.getElementById("password").value,
        fullName: document.getElementById("fullName").value,
        email: document.getElementById("email").value,
        role: "ADMIN",
        status: "ACTIVE"
    };

    fetch("/api/auth/register/admin", {
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
function registerAdmin() {

    const username = document.getElementById("username");
    const password = document.getElementById("password");
    const fullName = document.getElementById("fullName");
    const email = document.getElementById("email");

    let valid = true;

    function setError(input, message) {
        input.classList.add("is-invalid");
        input.nextElementSibling.innerText = message;
        valid = false;
    }

    // reset
    [username, password, fullName, email].forEach(el => {
        el.classList.remove("is-invalid");
        el.nextElementSibling.innerText = "";
    });

    // Username
    if (!/^[A-Za-z]+$/.test(username.value.trim())) {
        setError(username, "Only letters allowed (no numbers)");
    }

    // Password
    if (password.value.trim().length < 6) {
        setError(password, "Minimum 6 characters required");
    }

    // Full name
    if (fullName.value.trim() === "") {
        setError(fullName, "Full name is required");
    }

    // Email
    if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email.value.trim())) {
        setError(email, "Enter a valid email address");
    }

    if (!valid) return;

    const data = {
        username: username.value,
        password: password.value,
        fullName: fullName.value,
        email: email.value,
        role: "ADMIN",
        status: "ACTIVE"
    };

    fetch("/api/auth/register/admin", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    })
    .then(res => res.text())
    .then(msg => {
        showToast(msg);
        window.location.href = "/auth/login";
    });
}
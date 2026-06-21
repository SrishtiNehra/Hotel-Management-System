function registerCustomer() {

    const username = document.getElementById("username");
    const password = document.getElementById("password");
    const fullName = document.getElementById("fullName");
    const email = document.getElementById("email");
    const phoneNumber = document.getElementById("phoneNumber");

    let valid = true;

    function setError(input, message) {
        input.classList.add("is-invalid");
        input.nextElementSibling.innerText = message;
        valid = false;
    }

    // reset
    [username, password, fullName, email, phoneNumber].forEach(el => {
        el.classList.remove("is-invalid");
        el.nextElementSibling.innerText = "";
    });

    // Username (only letters)
    if (!/^[A-Za-z]+$/.test(username.value.trim())) {
        setError(username, "Only letters allowed (no numbers)");
    }

    // Password (min 6)
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

    // Phone number (10 digits)
    if (!/^[0-9]{10}$/.test(phoneNumber.value.trim())) {
        setError(phoneNumber, "Phone number must be 10 digits");
    }

    if (!valid) return;

    const data = {
        username: username.value,
        password: password.value,
        fullName: fullName.value,
        email: email.value,
        phoneNumber: phoneNumber.value,
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
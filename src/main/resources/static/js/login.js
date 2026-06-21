function login() {

    const username = document.getElementById("username");
    const password = document.getElementById("password");

    let valid = true;

    function setError(input, message) {
        input.classList.add("is-invalid");

        const feedback = input.parentNode.querySelector(".invalid-feedback");
        if (feedback) {
            feedback.innerText = message;
        }

        valid = false;
    }

    // reset
    [username, password].forEach(el => {
        el.classList.remove("is-invalid");

        const feedback = el.parentNode.querySelector(".invalid-feedback");
        if (feedback) feedback.innerText = "";
    });

    // Username validation
    if (username.value.trim() === "") {
        setError(username, "Username is required");
    }

    // Password validation
    if (password.value.trim().length < 6) {
        setError(password, "Password must be at least 6 characters");
    }

    if (!valid) return;

    const payload = {
        username: username.value,
        password: password.value
    };

    fetch("/api/auth/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(payload)
    })
    .then(res => {
        if (!res.ok) {
            throw new Error("Invalid credentials");
        }
        return res.json();
    })
    .then(response => {

        if (!response.token) {
            throw new Error("Login failed");
        }

        localStorage.setItem("token", response.token);
        localStorage.setItem("role", response.role);
        localStorage.setItem("customerId", response.customerId);

        showToast("Login Successful!");

        if (response.role === "ADMIN") {
            window.location.href = "/dashboard/admin";
        } else if (response.role === "CUSTOMER") {
            window.location.href = "/dashboard/customer";
        } else {
            alert("Invalid role");
        }
    })
    .catch(err => {
        console.error(err);
        alert(err.message || "Login Failed");
    });
}
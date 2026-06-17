function login() {

    const data = {
        username: document.getElementById("username").value,
        password: document.getElementById("password").value
    };

    fetch("/api/auth/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
    .then(res => res.json())
    .then(res => {

        // store token + role
        localStorage.setItem("token", res.token);
        localStorage.setItem("role", res.role);

        showToast("Login Successful!");

        
        if (res.role === "ROLE_ADMIN") {
            window.location.href = "/dashboard/admin";
        } 
        else if (res.role === "ROLE_CUSTOMER") {
            window.location.href = "/dashboard/customer";
        } 
        else {
            alert("Invalid role");
        }
    })
    .catch(err => {
        console.error(err);
        alert("Login Failed");
    });
}
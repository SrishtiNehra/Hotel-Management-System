function createReservation() {

    let roomId = localStorage.getItem("selectedRoom");

    let data = {
        bookingDate: new Date().toISOString().split("T")[0],
        plannedCheckIn: document.getElementById("checkIn").value,
        plannedCheckOut: document.getElementById("checkOut").value,
        customerId: 1,
        roomId: roomId
    };

    fetch("/api/reservations", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + localStorage.getItem("token")
        },
        body: JSON.stringify(data)
    })
    .then(res => res.json())
    .then(data => {
        alert("Room Booked Successfully!");
        window.location.href = "/customer/dashboard";
    });
}
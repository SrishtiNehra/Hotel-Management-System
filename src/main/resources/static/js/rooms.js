window.onload = function () {

    fetch("/api/rooms", {
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        }
    })
    .then(res => res.json())
    .then(data => {

        let container = document.getElementById("roomContainer");

        data.forEach(room => {

            container.innerHTML += `
                <div class="card">
                    <h3>Room ${room.roomNumber}</h3>
                    <p>Type: ${room.roomType}</p>
                    <p>Price: ₹${room.price}</p>
                    <p>Status: ${room.status}</p>

                    <button class="btn" onclick="bookRoom(${room.roomId})">
                        Book Now
                    </button>
                </div>
            `;
        });
    });
};
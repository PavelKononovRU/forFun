function getusers() {
    fetch("https://reqres.in/api/users?page=2").then(data => data.json())
        .then(x => console.log(x)).catch(error => console.log(error));
}

console.log("До")
getusers();
console.log("После")


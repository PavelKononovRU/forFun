async function getPrincipal() {
    let principal = await fetch('http://localhost:8080/admin/authentic').then(res=>res.json());
    let authName = document.querySelector('#auth_name');
    authName.innerHTML = principal.name;
    return principal;
}
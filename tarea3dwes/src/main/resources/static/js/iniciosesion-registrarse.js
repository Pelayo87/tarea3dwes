const urlParams = new URLSearchParams(window.location.search);
const action = urlParams.get('action');
const loginContainer = document.getElementById('login');
const registerContainer = document.getElementById('register');
const links = document.querySelectorAll('.link a');

links.forEach(link => {
    link.addEventListener('click', (e) => {
        e.preventDefault();
        const target = link.getAttribute('href').substring(1);

        if (target === 'register') {
            loginContainer.style.display = 'none';
            registerContainer.style.display = 'block';
        } else {
            loginContainer.style.display = 'block';
            registerContainer.style.display = 'none';
        }
    });
});

if (action === 'register') {
    loginContainer.style.display = 'none';
    registerContainer.style.display = 'block';
} else {
    loginContainer.style.display = 'block';
    registerContainer.style.display = 'none';
}
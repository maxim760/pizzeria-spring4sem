const toastBtn = document.querySelector(".toast-close")
if(toastBtn) {
    toastBtn.addEventListener("click", (e) => {
        e?.target?.closest?.(".toast")?.remove?.()
    })
}
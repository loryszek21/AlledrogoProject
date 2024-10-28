"use client";

function Footer() {
  return (
    <>
      <div className="dark:bg-dark mx-auto max-w-full px-6 py-24 sm:py-32 lg:px-8 dark:text-white">
        <div>
          <form action="">
            <div className="gird-cols-1 grid items-center justify-center gap-4 md:grid-cols-3">
              <div className="md:mb-6 md:ms-auto ">
                <p>
                  <strong>Sign up for our newsletter</strong>
                </p>
              </div>

              <div className="relative md:mb-6" data-twe-input-wrapper-init>
                <input
                  type="email"
                  className="peer block min-h-[auto] w-full rounded border-0 bg-transparent px-3 py-[0.32rem] leading-[1.6] outline-none transition-all duration-200 ease-linear focus:placeholder:opacity-100 peer-focus:text-primary data-[twe-input-state-active]:placeholder:opacity-100 motion-reduce:transition-none dark:text-white dark:placeholder:text-neutral-300 dark:autofill:shadow-autofill dark:peer-focus:text-primary [&:not([data-twe-input-placeholder-active])]:placeholder:opacity-0"
                  id="exampleFormControlInputEmail"
                  placeholder="Email address"
                />
                <label
                  htmlFor="exampleFormControlInputEmail"
                  className="pointer-events-none absolute left-3 top-0 mb-0 max-w-[90%] origin-[0_0] truncate pt-[0.37rem] leading-[1.6] text-neutral-500 transition-all duration-200 ease-out peer-focus:-translate-y-[0.9rem] peer-focus:scale-[0.8] peer-focus:text-primary peer-data-[twe-input-state-active]:-translate-y-[0.9rem] peer-data-[twe-input-state-active]:scale-[0.8] motion-reduce:transition-none dark:text-neutral-400 dark:peer-focus:text-primary"
                >
                  Email address
                </label>
              </div>

              <div className="mb-6 md:me-auto">
                <button
                  type="submit"
                  className="inline-block rounded px-6 pb-[6px] pt-2 text-xs font-medium uppercase leading-normal text-surface shadow-dark-3 shadow-black/30 transition duration-150 ease-in-out hover:shadow-dark-1 focus:shadow-dark-1 focus:outline-none focus:ring-0 active:shadow-1  bg-button text-white"
                  data-twe-ripple-init
                  data-twe-ripple-color="light"
                >
                  Subscribe
                </button>
              </div>
            </div>
          </form>
        </div>
        <p className="text-center text-xs leading-5 text-gray-500">
          &copy; Alledrogo, All rights reserved.
        </p>
      </div>
    </>
  );
}

export default Footer;

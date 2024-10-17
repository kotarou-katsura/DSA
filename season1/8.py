def print_t(n, prefix=""):
    if n == 0:
        print(prefix)
    else:
        print_t(n - 1, prefix + "0\t")
        print_t(n - 1, prefix + "1\t")


n = int(input("enter the number of variables:"))
print_t(n)

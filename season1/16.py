def print_s(s, subset, i):
    if i == len(s):
        print(subset)
        return
    print_s(s, subset, i + 1)
    print_s(s, subset + [s[i]], i + 1)

set = input("enter the set(e.g. a b c)  :").split()
print_s(set, [], 0)

def calculator(s, x):
    s = s.replace('x', str(x))

    if '+' in s:
        parts = s.rsplit('+', 1)
        return calculator(parts[0], x) + calculator(parts[1], x)
    elif '-' in s:
        parts = s.rsplit('-', 1)
        return calculator(parts[0], x) - calculator(parts[1], x)
    elif '*' in s:
        parts = s.rsplit('*', 1)
        return calculator(parts[0], x) * calculator(parts[1], x)
    elif '/' in s:
        parts = s.rsplit('/', 1)
        return calculator(parts[0], x) / calculator(parts[1], x)
    else:
        if s.strip().isdigit() or (s.strip()[0] == '-' and s.strip()[1:].isdigit()):
            return float(s)
        else:
            return 0


# s = "x*x+x+x-x*x"
s = input("enter the string (just use x + - / *):")
x = input("enter the number:")
print(calculator(s, x))

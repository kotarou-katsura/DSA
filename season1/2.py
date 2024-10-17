def average(arr, s, c):
    if len(arr) == c:
        return s / c
    else:
        s += arr[c]
        c += 1
        return average(arr, s, c)


array = input("please enter the numbers :").split()
array = [int(item) for item in array]
print(average(array, 0, 0))

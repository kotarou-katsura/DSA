def find_combinations(amount, coins, seen):
    if amount == 0:
        combination = tuple(sorted(coins))
        if combination not in seen:
            print(combination)
            seen.add(combination)
        return
    if amount < 0:
        return

    for coin in [2, 5, 10]:
        find_combinations(amount - coin, coins + [coin], seen)



seen = set()
find_combinations(20, [], seen)


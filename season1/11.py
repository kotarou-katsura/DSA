def find_combinations(amount, coins):
    if amount == 0:
        print(coins)
        return
    if amount < 0:
        return

    for coin in [2, 5, 10]:
        find_combinations(amount - coin, coins + [coin])


find_combinations(20, [])

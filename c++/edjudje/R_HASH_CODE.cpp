#include <cstdio>
#define long unsigned long long


long next_x()
{
    const long A = 1103515245;
    const long B = 12345;
    static long x = 1;
    long ret = x;
    x = (x * A + B) % (1ll << 32);
    return ret;
}


char next_c(long x)
{
    return 'a' + (x >> 16) % 26;
}


int main()
{
#ifdef LALALA
    freopen("r.txt", "r", stdin);
#endif // LALALA

    const int C = 1009;
    const int D = 1e9 + 7;
    long n, k, h;
    while (scanf("%llu%llu%llu", &n, &k, &h) != -1)
    {
        long cur_h = 0;
        long c = 1, hc = 1;
        int i = 0;
        char s[1000005];
        long pre_h[1000005] = {};
        for (; i < k; ++i)
        {
            s[i] = next_c(next_x());
            pre_h[i] = s[i] * c % D;
            c = c * C % D;
            cur_h = (cur_h + pre_h[i]) % D;
        }
        for (; cur_h != h && i < n; ++i)
        {
            s[i] = next_c(next_x());
            pre_h[i] = s[i] * c % D;
            c = c * C % D;
            cur_h = (cur_h + pre_h[i] + D - pre_h[i - k]) % D;
            h = h * C % D;
        }
        if (cur_h == h)
        {
            for (int j = i - k; j < i; ++j)
            {
                printf("%c", s[j]);
            }
        }
        else
        {
            printf("0");
        }
        printf("\n");
    }
}

/*

Компания, выпускающая мобильные телефоны, присваивает каждому изделию серийный номер, который вычисляется по следующей формуле:

(x0*A + x1*A2+x2*A3 + x3*A4 + x4*A5+ x5*A6) mod B

где x0 – год выпуска (2000 ≤ x0 ≤ 3000), x1 – месяц выпуска (1 ≤ x1 ≤ 12), x2 – день выпуска (1 ≤ x2 ≤ 31), x3 – час выпуска (0 ≤ x3 ≤ 23), x4 – минута выпуска (0 ≤ x4 ≤ 59), x5 – секунда выпуска (0 ≤ x5 ≤ 59), A – константа (10 ≤ A ≤ 109) B = 109+7

В связи с невнимательностью одного из сотрудников компании все телефоны, выпущенные 24.09.2015, оказались прошиты неправильной прошивкой. Ваша задача из списка серийных номеров телефонов отобрать все такие, которые, возможно, были выпущены 24.09.2015, и вывести количество этих телефонов

*/

#include <iostream>


inline int calculateHashes(int *h, int a)
{
    const int x0 = 2015, x1 = 9, x2 = 24, B = (int)1e9 + 7;
    int a2 = a * 1ll * a % B;
    int a3 = a2 * 1ll * a % B;
    int a4 = a2 * 1ll * a2 % B;
    int a5 = a3 * 1ll * a2 % B;
    int a6 = a3 * 1ll * a3 % B;

    for (int x3 = 0, i = 0; x3 < 24; ++x3)
    {
        for (int x4 = 0; x4 < 60; ++x4)
        {
            for (int x5 = 0; x5 < 60; ++x5, ++i)
            {
                h[i] = x0 * 1ll * a % B;
                h[i] += x1 * 1ll * a2 % B;
                h[i] %= B;
                h[i] += x2 * 1ll * a3 % B;
                h[i] %= B;
                h[i] += x3 * 1ll * a4 % B;
                h[i] %= B;
                h[i] += x4 * 1ll * a5 % B;
                h[i] %= B;
                h[i] += x5 * 1ll * a6 % B;
                h[i] %= B;
            }
        }
    }
}

inline void justMerge(int *h, int left, int mid, int right)
{
    int *t = new int[right - left + 1];
    int k = 0, i = left, j = mid + 1;
    while (i <= mid && j <= right)
    {
        t[k++] = h[h[i] < h[j] ? i++ : j++];
    }
    while (i <= mid) t[k++] = h[i++];
    for (int q = 0; q < k; ++q) h[q + left] = t[q];
    delete[] t;
}


inline void mergeSort(int *h, int left, int right)
{
    if (right - left < 1) return;
    int mid = (left + right) / 2;
    mergeSort(h, left, mid);
    mergeSort(h, mid + 1, right);
    justMerge(h, left, mid, right);
}


inline int binSearch(int *a, int value, int left, int right)
{
    if (left > right) return 0;
    int mid = (left + right) / 2;
    if (a[mid] == value) return 1;
    if (a[mid] < value) return binSearch(a, value, mid + 1, right);
    return binSearch(a, value, left, mid - 1);
}


int main()
{
    std::ios_base::sync_with_stdio(0);
    int n, a;
    while (std::cin >> a >> n)
    {
        const int N = 24 * 60 * 60;
        int *h = new int[N];
        calculateHashes(h, a);
        mergeSort(h, 0, N - 1);
        int x, ans = 0;
        for (int i = 0; i < n; ++i)
        {
            std::cin >> x;
            ans += binSearch(h, x, 0, N - 1);
        }
        std::cout << ans << "\n";
        delete[] h;
    }
}

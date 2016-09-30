package study_03;

public class No2 {

	private String value;

	/*
	 * compareToメソッド
	 *
	 * Stringが引数文字列よりも
	 * 		- 前にある場合、結果は負の整数。
	 * 		- 後にある場合、結果は正の整数。
	 * 		- 等しい場合は0。
	 *
	 * ～～～「辞書的の順序」の定義～～～
	 * ２つの文字列が異なる場合
	 * それぞれの文字列に対して有効なインデックスに位置する
	 * 文字列が異なる or　文字列の長さが異なる or ←の両方異なる　のいずれかが該当。
	 *
	 * １つ以上のインデックスの位置にある文字が異なる場合
	 * このうちのもっとも小さいインデックスをKとすると、
	 * "＜"演算子を使用して「より小さい」値と判定される位置Kにある文字を持つ文字列が、
	 * もう一方の文字列より辞書的に前になる。
	 * この場合、compareToは２つの文字列で位置Kにある２つの文字の値の差を返す。
	 * これは以下の値になる。
	 * 		this.charAt(k) - anotherString.charAt(k)
	 *
	 * 有効なすべてのインデックス位置における文字列が同じ場合は、短いほうの文字列が辞書的に前になる。
	 * この場合、compareToは文字列の長さを返す。
	 * これは以下の値になる。
	 * 		this.length() - anotherString.length()
	 *
	 * @return
	 * 引数文字列がこの文字列に等しい場合は値0。
	 * この文字列が文字列引数よりも辞書式に小さい場合は、０より小さい値。
	 * この文字列が文字列引数よりも辞書式に大きい場合は、０より大きい値。
	 */
	public int compareTo(String anotherString) {

		// Stringの長さ
		int len1 = value.length;
		// TODO:１
		// ここにanotherStringの長さを代入する
		int len2 = anotherString.value.length;

		//　どちらが小さいか比較する
		int lim = Math.min(len1, len2);

		// Stringと文字列をそれぞれchar[]配列に代入する
		char v1[] = value;
		char v2[] = anotherString.value;

		// ｋ：もっとも小さいインデックス
		int k = 0;

		// Stringの長さ分ループ
		while (k < lim) {

			char c1 = v1[k];	// TODO:２
			char c2 = v2[k];	// TODO:３

			// ２つの文字が異なる場合
			if (c1 != c2) {
				// TODO:４
				return this.charAt(k) - anotherString.charAt(k);
			}
			k++;
		}
		// TODO:５
		return XXX;

	}

}

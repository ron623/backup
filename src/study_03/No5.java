package study_03;

public class No5 {

	/*
	 * 指定されたindexから始まる部分文字列の先頭から始まるかどうかテストする
	 *
     * @param   prefix    接頭辞
     * @param   toffset   この文字列の比較を開始する位置
     * @return  引数によって表される文字シーケンスが、インデックス toffset で始まる
	 *
	 * このオブジェクトの部分文字列のときはtrue,そうでなければfalse
	 * tooffsetが負の値である場合、あるいはStringオブジェクトの長さよりも長い場合false
	 *  そうでない場合は結果は次の式の結果と同じ
	 * this.subString(toffset).startsWith(prefix)
	 */
	public boolean startsWith(String prefix, int toffset) {

		// Stringオブジェクト
		char ta[] = value;
		// toffset(文字列の長さ)
		int to = toffset;

		// TODO:③
		//char pa[] = prefix.value

		int po = 0;
		int pc = prefix.value.length;

		// tooffsetが 負の値　or Stringオブジェクトの長さよりも長い場合
		if (( toffset < 0 ) || (toffset > value.length -pc)){

			// TODO:①
			//return false;
		}

		while (equals(--pc >= 0)) {

			// TODO:②
			if(ta[to++] != pa[po++]){
				return false;
			}

		}

	}
}

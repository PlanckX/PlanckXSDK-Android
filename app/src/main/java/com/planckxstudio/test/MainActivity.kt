package com.planckxstudio.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.planckxstudio.sdk.PlanckxStudio
import com.planckxstudio.sdk.http.HttpCallback


class MainActivity : AppCompatActivity() {
    private val TAG = "test"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tvMsg = findViewById<TextView>(R.id.tv_msg)
        findViewById<Button>(R.id.btn_judgeBindStatus).setOnClickListener {
            showDialog("123456") { edit ->
                PlanckxStudio.judgeBindStatus(edit, object : HttpCallback {
                    override fun success(str: String) {
                        Log.e(TAG, str)
                        tvMsg.text = str
                    }

                    override fun error(e: Throwable) {
                        errorLog(e)
                        makeToast(e)
                    }
                })
            }
        }

        findViewById<Button>(R.id.btn_players_nft).setOnClickListener {
            showDialog("123456") { edit ->
                PlanckxStudio.searchPlayersNft(edit, object : HttpCallback {
                    override fun success(str: String) {
                        Log.e(TAG, str)
                        tvMsg.text = str
                    }

                    override fun error(e: Throwable) {
                        errorLog(e)
                        makeToast(e)
                    }
                })
            }
        }

        findViewById<Button>(R.id.btn_games_nft).setOnClickListener {
            PlanckxStudio.searchGamesNft(object : HttpCallback {
                override fun success(str: String) {
                    Log.e(TAG, str)
                    tvMsg.text = str
                }

                override fun error(e: Throwable) {
                    errorLog(e)
                    makeToast(e)
                }
            })
        }

        findViewById<Button>(R.id.btn_token_nft).setOnClickListener {
            showDialog("6") { edit ->
                PlanckxStudio.searchTokenNft(edit, object : HttpCallback {
                    override fun success(str: String) {
                        Log.e(TAG, str)
                        tvMsg.text = str
                    }

                    override fun error(e: Throwable) {
                        errorLog(e)
                        makeToast(e)
                    }
                })
            }
        }
    }

    private fun showDialog(text: String? = null, callback: (String) -> Unit) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Input")
        val edit = EditText(this)
        edit.setText(text)
        builder.setView(edit)
        builder.setPositiveButton("Confirm") { p0, _ ->
            p0.dismiss()
            callback.invoke(edit.editableText.toString())
        }
        builder.setNegativeButton(
            "Cancel"
        ) { p0, _ -> p0.dismiss() }
        builder.show()
    }

    fun errorLog(e: Throwable) {
        e.message?.let { Log.e(TAG, it) } ?: let {
            Log.e(TAG, e.javaClass.name)
        }
    }

    fun makeToast(e: Throwable) {
        makeToast(e.message ?: let { e.javaClass.name })
    }

    fun makeToast(msg: String) {
        Toast.makeText(this@MainActivity, msg, Toast.LENGTH_LONG).show()
    }
}

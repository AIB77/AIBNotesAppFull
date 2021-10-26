package com.example.aibnotesappfull

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var RV: RecyclerView
    lateinit var EDT: EditText
    lateinit var BTN: Button
    private lateinit var db: DBHlper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RV=findViewById(R.id.rv)
        EDT=findViewById(R.id.edt)
        BTN=findViewById(R.id.btn)
        db = DBHlper(this)


        BTN.setOnClickListener {

            if(EDT.text.isNotBlank())
            {
                postNote()
            }
            else
            {
                Toast.makeText(this, "Enter The Note", Toast.LENGTH_LONG).show()
            }
            updateRV()

        }
    }
    private fun updateRV(){
        RV.adapter = RecyclerViewAdapter(this, getItemsList())
        RV.layoutManager = LinearLayoutManager(this)
    }

    private fun getItemsList(): ArrayList<NoteModel1>{
        return db.viewNotes()
    }

    private fun postNote(){

        db.addNote(NoteModel1(0, EDT.text.toString()))
        EDT.text.clear()
        Toast.makeText(this, "Note Added Successfully", Toast.LENGTH_LONG).show()
        updateRV()
    }

    private fun editNote(noteID: Int, noteText: String){
        db.updateNote(NoteModel1(noteID, noteText))
        Toast.makeText(this, "Note Updated Successfully", Toast.LENGTH_LONG).show()
        updateRV()
    }


    fun raiseDialog(id: Int)
    {
        val dialogBuilder = AlertDialog.Builder(this)
        val updatedNote = EditText(this)
        updatedNote.hint = "Enter New Text"
        dialogBuilder
            .setCancelable(false)
            .setPositiveButton("Save", DialogInterface.OnClickListener {
                    _, _ -> editNote(id, updatedNote.text.toString())
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                    dialog, _ -> dialog.cancel()
            })
        val alert = dialogBuilder.create()
        alert.setTitle("Update Note")
        alert.setView(updatedNote)
        alert.show()
    }
    fun deleteNote(noteID: Int){
        db.deleteNote(NoteModel1(noteID, ""))
        Toast.makeText(this, "Note Deleted Successfully", Toast.LENGTH_LONG).show()
        updateRV()
    }
}
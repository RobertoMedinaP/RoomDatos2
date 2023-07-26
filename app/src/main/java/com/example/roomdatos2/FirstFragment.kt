package com.example.roomdatos2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomdatos2.Model.Task
import com.example.roomdatos2.Model.TaskDataBase
import com.example.roomdatos2.databinding.FragmentFirstBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        //se instancia la base de datos
        val database= Room.databaseBuilder(
            requireContext().applicationContext,
            TaskDataBase::class.java,
        "task_database")
                //uso en hilo principal
                //deberia hacerse con corrutina
            //.allowMainThreadQueries()
            .build()



        //se inserta una nueva tarea
        val newTask= Task(
            task="NombreTask",
            descripcion = "Prueba de insercion",
            date = "26-07-2023"

        )

        //para usar con corrutina
        GlobalScope.launch (Dispatchers.IO) {
            database.getTaskDao().insertTask(newTask)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
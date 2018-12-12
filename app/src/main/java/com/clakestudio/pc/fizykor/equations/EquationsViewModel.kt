package com.clakestudio.pc.fizykor.equations

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.content.Context
import android.databinding.Bindable
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.util.Log
import android.widget.Toast
import com.clakestudio.pc.fizykor.SingleLiveEvent
import com.clakestudio.pc.fizykor.data.Equation
import com.clakestudio.pc.fizykor.data.source.EquationsRepository
import com.clakestudio.pc.fizykor.util.AppSchedulersProvider
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable

class EquationsViewModel(
        context: Application,
        private val equationsRepository: EquationsRepository
) : AndroidViewModel(context) {

    private val isDataLoadingError = ObservableBoolean(false)
    private val context: Context = context.applicationContext
    internal val openFlashCardsEvent = SingleLiveEvent<String>()


    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    var equations: ObservableArrayList<Equation> = ObservableArrayList()

    var rawEquations: ArrayList<Equation> = ArrayList()

    var flashCardsEvent: SingleLiveEvent<Void> = SingleLiveEvent()


    fun start() {
       //testBaseInjection()
        if (equations.isEmpty())
            loadData()

    }

    fun testBaseInjection() {

        equationsRepository.saveEquation(Equation("Kinematyka", "Ruch", "Ruch jednostajny", "$\\{v↖{→}={∆s}/t [m/s]}$ <br> $\\{v↖{→}}$ prędkość, $∆s$ przemieszczenie/przebyta droga, $\\t$ czas"))
        equationsRepository.saveEquation(Equation("Kinematyka", "Ruch", "Ruch jednostajny zmienny", "$\\{v↖{→}=v_0+at [m/s]}$ , s$\\a↖{→}={∆v}/t [m/s^2]$ <br> $\\{v↖{→}}$ prędkość, $\\v_0$ prędkość początkowa, $\\a↖{→}$ przyśpieszenie, $\\t$ czas, dla $\\a↖{→}>0$ ruch jednostajnie przyśpieszony, dla $\\a↖{→}<0$ ruch jednostajnie opóźniony"))
        equationsRepository.saveEquation(Equation("Kinematyka", "Ruch", "Droga w ruchu jednostajnie zmiennym", "$\\s = v_0t +↙{-} {at^2}/2$ $[m]$,   $\\s = {v_0 + v_k}/2 t$,   $\\s = {v_k^2-v_0^2}/{2a}$ <br> $\\s$ droga , $\\a↖{→}$ przyśpieszenie, $\\t$ czas, $\\v_k$ prędkość końcowa, $\\v_0$ prędkość początkowa <br> $\\s_1:s_2:s_3 ..s_n = 1:3:5..n$ <br> Drogi przebyte przez ciało w ruchu jednostajnie przyśpieszonym bez prędkości początkowej mają się do siebie jak kolejne liczby nieparzyste."))
        equationsRepository.saveEquation(Equation("Kinematyka", "Ruch", "Równania ruchu", "$\\{v↖{→}}(t)=v_0+↙{-}at$ $[m/s]$ <br> $\\{v(t)}$ prędkość od czasu, $\\a↖{→}$ przyśpieszenie, $\\t$ czas <br> $\\x(t)=x_0+s$ $[m]$ <br> $\\x(t)$ położenie od czasu, $\\x_0$ położenie początkowe, w miejsce $\\s$ wstawiamy wzór na drogę w zależności od ruchu jakim porusza się rozpatrywane ciało"))
        equationsRepository.saveEquation(Equation("Kinematyka", "Ruch", "Szybkość srednia", "Szybkość średnia to stosunek całkowitej drogi przebytej podczasu ruchu do całkowitego czasu trwania tego ruchu."))
        equationsRepository.saveEquation(Equation("Kinematyka", "Rzuty", "Spadek swobodny", "$\\v↖{→}(t)=v_0+↙{-}at$ $[m/s]$ <br> $\\v(t)$ prędkość od czasu, $\\a↖{→}$ przyśpieszenie, $\\t$ czas <br> $\\x(t)=x_0+s$ $[m]$ <br> $\\x(t)$ położenie od czasu, $\\x_0$ położenie początkowe, w miejsce $\\s$ wstawiamy wzór na drogę w zależności od ruchu jakim porusza się rozpatrywane ciało"))
        equationsRepository.saveEquation(Equation("Kinematyka", "Rzuty", "Rzut poziomy", "Przy spadku swobodnym, w pionie, bez oporów ruchu $\\a↖{→}=g↖{→}$, $\\v↖{→}_0 = 0$. Spadek swobodny jest ruchem jednostajnie przyśpieszonym, Dla rzutu pionowego $\\h(t)=x_0+↙{-}v_0↖{→}t+↙{-}{g↖{→}t^2}/2$, gdzie $\\g$ to przyśpiesznie ziemskie."))
        equationsRepository.saveEquation(Equation("Kinematyka", "Rzuty", "Rzut ukośny", "$\\Z=v↖{→}_ot$ ,  $\\v↖{→}_{ky}=g↖{→}t$,  $\\tgα={v_y}/{v_x}={g_t}/v_o$,   $\\v↖{→}_c=√{v↖{→}_x^2+v↖{→}_y^2}$ <br> $\\Z$ zasięg, $\\v↖{→}_{ky}$ prędkość końcowa pionowej składowej wekotra prędkości całkowitej, $\\tgα$ tangens kąta pod jakim ciało uderzy w ziemię, $\\v↖{→}_c$ prędkość całkowita, która zawsze jest styczna do toru, $\\v↖{→}_x$ pozioma składowa wektora prędkośi całkowitej, $\\v↖{→}_y$ pionowa składowa wektora prędkości całkowitej"))
        equationsRepository.saveEquation(Equation("Kinematyka", "Ruch po okręgu", "Ruch po okręgu", "$\\{v↖{→}}_{0y}=v_0sinα$, $\\{v↖{→}}_{0x}=v_0cosα$, $\\{t_{wz}}={v↖{→}_0sinα}/g↖{→}$, $\\{t_{cal}}={2v_0sinα}/g$, $\\h_{max}={v_0^2sin^2α}/{2g}$, $\\Z={v_0^2sin2α}/g$ <br> $\\{α}$ kąt pod jakim ciało jest nachylone do osi O:X, $\\{v↖{→}}_0$, $\\v_0$ prędkość początkowa, $\\{v↖{→}}_{0x}$  prędkość początkowa poziomej skłądowej prędkości, $\\{v↖{→}}_{0y}$  prędkość początkowa pionowej składowej prędkości, $\\{t_{wz}}$ czas wznoszenia, $\\{t_{cal}}$ czas całkowity, $\\h_{max}$ maksymalna wysokość na jaką wzniesie się ciało. Czas wznoszenia jest równy czasowi spadania ciała."))
        equationsRepository.saveEquation(Equation("Kinematyka", "Ruch po okręgu", "Ruch po okręgu", "$\\{v↖{→}}= s/t= {2πr}/T = {2πrf}$, $\\f=1/T$ <br> $\\v↖{→}$ prędkość ciała w ruchu po okręgu, $\\f$ czestotliwość, $\\r$ promień okręgu, $\\T$ okres, czas jednego pełnego ruchu,"))


    }

    private fun loadData() {
        var disposable = equationsRepository.getAllEquations()
                .subscribeOn(AppSchedulersProvider.ioScheduler())
                .subscribe(
                        {
                            addEquations(it)
                        },
                        { showToast("Error retriving data occured") }
                )
        compositeDisposable.add(disposable)
    }

    private fun addEquations(equations: List<Equation>) {
        rawEquations.addAll(equations)
        filterEquations("Ruch")
    }

    fun filterEquations(filtering: String) {
        equations.clear()
        equations.addAll(rawEquations.filter { it.subsection == filtering })

    }

    fun openFlashCards() {
        flashCardsEvent.call()
    }


    private fun showToast(errorMessage: String) = Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()

    //val equations : ObservableList<Equation> = ObservableArrayList()

    /**
     * Backend ->
     * */
}

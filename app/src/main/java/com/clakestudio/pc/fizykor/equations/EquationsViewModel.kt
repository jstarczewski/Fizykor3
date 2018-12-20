package com.clakestudio.pc.fizykor.equations

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import com.clakestudio.pc.fizykor.SingleLiveEvent
import com.clakestudio.pc.fizykor.data.Equation
import com.clakestudio.pc.fizykor.data.source.EquationsRepository
import com.clakestudio.pc.fizykor.util.AppSchedulersProvider
import io.reactivex.disposables.CompositeDisposable
import java.util.*

class EquationsViewModel(
        private val equationsRepository: EquationsRepository
) : ViewModel() {

    private val isDataLoadingError = ObservableBoolean(false)
    internal val openFlashCardsEvent = SingleLiveEvent<String>()


    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    var equations: ObservableArrayList<Equation> = ObservableArrayList()
    private var rawEquations: ArrayList<Equation> = ArrayList()
    private var isDataLoaded = false
    var flashCardsEvent: SingleLiveEvent<Void> = SingleLiveEvent()


    fun start() {
       //  testBaseInjection()
       if (!isDataLoaded) loadData()
    }

    fun testBaseInjection() {


        equationsRepository.saveEquation(Equation("Kinematyka", "Ruch jednostajny", "$\\{v↖{→}={∆s}/t [m/s]}$ <br> $\\{v↖{→}}$ prędkość, $∆s$ przemieszczenie/przebyta droga, $\\t$ czas"))
        equationsRepository.saveEquation(Equation("Kinematyka", "Ruch jednostajny zmienny", "$\\{v↖{→}=v_0+at [m/s]}$ , s$\\a↖{→}={∆v}/t [m/s^2]$ <br> $\\{v↖{→}}$ prędkość, $\\v_0$ prędkość początkowa, $\\a↖{→}$ przyśpieszenie, $\\t$ czas, dla $\\a↖{→}>0$ ruch jednostajnie przyśpieszony, dla $\\a↖{→}<0$ ruch jednostajnie opóźniony"))
        equationsRepository.saveEquation(Equation("Kinematyka", "Droga w ruchu jednostajnie zmiennym", "$\\s = v_0t +↙{-} {at^2}/2$ $[m]$,   $\\s = {v_0 + v_k}/2 t$,   $\\s = {v_k^2-v_0^2}/{2a}$ <br> $\\s$ droga , $\\a↖{→}$ przyśpieszenie, $\\t$ czas, $\\v_k$ prędkość końcowa, $\\v_0$ prędkość początkowa <br> $\\s_1:s_2:s_3 ..s_n = 1:3:5..n$ <br> Drogi przebyte przez ciało w ruchu jednostajnie przyśpieszonym bez prędkości początkowej mają się do siebie jak kolejne liczby nieparzyste."))
        equationsRepository.saveEquation(Equation("Kinematyka", "Równania ruchu", "$\\{v↖{→}}(t)=v_0+↙{-}at$ $[m/s]$ <br> $\\{v(t)}$ prędkość od czasu, $\\{a↖{→}}$ przyśpieszenie, $\\t$ czas <br> $\\{x(t)}=x_0+s$ $\\{[m]}$ <br> $\\{x(t)}$ położenie od czasu, $\\x_0$ położenie początkowe, w miejsce $\\{s}$ wstawiamy wzór na drogę w zależności od ruchu jakim porusza się rozpatrywane ciało"))
        equationsRepository.saveEquation(Equation("Kinematyka", "Spadek swobodny i rzut pionowy", "$\\v↖{→}(t)=v_0+↙{-}at$ $[m/s]$ <br> $\\v(t)$ prędkość od czasu, $\\a↖{→}$ przyśpieszenie, $\\t$ czas <br> $\\x(t)=x_0+s$ $[m]$ <br> $\\x(t)$ położenie od czasu, $\\x_0$ położenie początkowe, w miejsce $\\s$ wstawiamy wzór na drogę w zależności od ruchu jakim porusza się rozpatrywane ciało"))
        equationsRepository.saveEquation(Equation("Kinematyka", "Rzut poziomy", "Przy spadku swobodnym, w pionie, bez oporów ruchu $\\a↖{→}=g↖{→}$, $\\v↖{→}_0 = 0$. Spadek swobodny jest ruchem jednostajnie przyśpieszonym, Dla rzutu pionowego $\\h(t)=x_0+↙{-}v_0↖{→}t+↙{-}{g↖{→}t^2}/2$, gdzie $\\g$ to przyśpiesznie ziemskie."))
        equationsRepository.saveEquation(Equation("Kinematyka", "Rzut ukośny", "$\\Z=v↖{→}_ot$ ,  $\\v↖{→}_{ky}=g↖{→}t$,  $\\tgα={v_y}/{v_x}={g_t}/v_o$,   $\\v↖{→}_c=√{v↖{→}_x^2+v↖{→}_y^2}$ <br> $\\Z$ zasięg, $\\v↖{→}_{ky}$ prędkość końcowa pionowej składowej wekotra prędkości całkowitej, $\\tgα$ tangens kąta pod jakim ciało uderzy w ziemię, $\\v↖{→}_c$ prędkość całkowita, która zawsze jest styczna do toru, $\\v↖{→}_x$ pozioma składowa wektora prędkośi całkowitej, $\\v↖{→}_y$ pionowa składowa wektora prędkości całkowitej"))
        equationsRepository.saveEquation(Equation("Kinematyka", "Ruch po okręgu", "$\\{v↖{→}}_{0y}=v_0sinα$, $\\{v↖{→}}_{0x}=v_0cosα$, $\\{t_{wz}}={v↖{→}_0sinα}/g↖{→}$, $\\{t_{cal}}={2v_0sinα}/g$, $\\h_{max}={v_0^2sin^2α}/{2g}$, $\\Z={v_0^2sin2α}/g$ <br> $\\{α}$ kąt pod jakim ciało jest nachylone do osi O:X, $\\{v↖{→}}_0$, $\\v_0$ prędkość początkowa, $\\{v↖{→}}_{0x}$  prędkość początkowa poziomej skłądowej prędkości, $\\{v↖{→}}_{0y}$  prędkość początkowa pionowej składowej prędkości, $\\{t_{wz}}$ czas wznoszenia, $\\{t_{cal}}$ czas całkowity, $\\h_{max}$ maksymalna wysokość na jaką wzniesie się ciało. Czas wznoszenia jest równy czasowi spadania ciała."))
        equationsRepository.saveEquation(Equation("Kinematyka", "Szybkość srednia", "Szybkość średnia to stosunek całkowitej drogi przebytej podczasu ruchu do całkowitego czasu trwania tego ruchu."))
        equationsRepository.saveEquation(Equation("Kinematyka", "Przyspieszenie dośrodkwoe", "$\\{a_d}↖{→}=v^2/r$ $[m/s^2]$ <br> $\\{a_d}↖{→}$ przyśpieszenie dośrodkowe, $\\r$ promień, $\\v$ prędkość"))

        // Dynamika

        equationsRepository.saveEquation(Equation("Dynamika", "Pierwsza zasada dynamiki", "$\\{F↖{→}}_w=0$ to w izolowanym układzie ciało/punkt materialny spoczywa, lub porusza się ruchem jednostajnym, $\\{F↖{→}}_w$ siła wypadkowa"))
        equationsRepository.saveEquation(Equation("Dynamika", "Druga zasada dynamiki i pęd", "$\\{a↖{→}}=F↖{→}/m [{{kg*m/{s^2}}/{kg}=N/{kg}]$ <br> $\\{F↖{→}}={∆p↖{→}}/{t}$ <br> $\\{p↖{→}}=mv↖{→}$ $[kg*{m/s}]$ <br> $\\{F↖{→}}$ siła, $\\{a↖{→}}$ przyśpieszenie, $\\m$ masa, $∆p$ zmiana pędu, $\\t$ czas, $\\N$ Newton, jednostka siły <br> $\\{p↖{→}}=p↖{→}_1+p↖{→}_{2}...p↖{→}_n={const}↖{→}$ <br> Suma pędów ciał wchodzących w skład układu izolowanego jest stała. <br> $\\{p↖{→}}$ pęd, $\\{v↖{→}}$ prędkość, $\\m$ masa, $\\{p↖{→}}_c$ pęd całkowity"))
        equationsRepository.saveEquation(Equation("Dynamika", "Trzecia zasada dynamiki", "$\\F↖{→}_{AB}=-F↖{→}_{BA}$ <br> $\\F↖{→}_{AB}$ siła z jaką ciało A działa na ciało B, $\\F↖{→}_{BA}$ siła z jaką ciało B działa na ciało"))
        equationsRepository.saveEquation(Equation("Dynamika", "Dynamiczne równania ruchu", "Dla dwóch bloczków o masach $\\m_1$ i $\\m_2$ połączonych nierozciągliwą, lekką nitką, bez siły tarcia ciągniętych siłą $\\F↖{→}$, zaczepioną o drugi bloczek, o zwrocie skierowanym w prawo <br> $\\F_{w1}=F_n$, $\\F_{w2}=F-F_n$ <br> $\\m_1a=F_n$, $\\m_2a=F-F_n$ <br>  $\\m_1a+m_2a=F ⇒ a(m_1+m_2)=F ⇒$ $\\a=F/{m_1+m_2}$ <br> $\\F_{w1}$, $\\F_{w2}$ siła wypadkowa pierwsza u druga, $\\F_n$ siła naciągu miedzy bloczkami, $\\a_1$, $\\a_2$ przyśpieszenie pierwszego, drugie ciała, $\\a$ przyśpieszenie układu"))
        equationsRepository.saveEquation(Equation("Dynamika", "Tarcie", "$\\T=μF_n$ <br> $\\T↖{→}_s=μ_sF_n$, $\\T↖{→}_k=μ_kF_n$ <br> $\\T↖{→}$ tarcie, $\\μ$ współczynnik tarcia, $\\F_n$ siła nacisku, $\\T↖{→}_s$ tarcie statyczne, $\\μ_s$ wsp. tarcia statycznego, $\\T↖{→}_k$ tarcie kinetyczne $\\μ_k$ wsp. tarcia kinetycznego <br> $\\T_{smax}>T_{kmax}$"))
        equationsRepository.saveEquation(Equation("Dynamika", "Równania ruchu", "Dla ciała leżącego na boku $\\S$ równi pochyłej o krótszej przyprostokątnej $\\h$ i dłuższej $\\l$, przeciwprostokątnej $\\S$, oraz kącie $\\α$, dla którego $\\sinα=h/s$ <br> $\\F↖{→}_g=mg$,  $\\sinα=F_s/F_g ⇒ F↖{→}_s=mgsinα$, $\\cosα=F_n/F_g ⇒ F↖{→}_n=mgcosα$, $\\T↖{→}=μmgcosα$ <br> $\\F↖{→}_n$ siła nacisku, $\\F↖{→}_s$ siła zsuwająca, $\\F↖{→}_g$ siła grawitacji, $\\T↖{→}$ siła tarcia, $\\μ$ wsp. tarcia na równi, na ciało wjeżdżające na równię działa jedynie siła zsuwająca."))
        equationsRepository.saveEquation(Equation("Dynamika", "Nieważkość i winda", "Dla windy jadącej w górę $\\F↖{→}_n=mg+ma$, Dla windy jadącej w dół $\\F↖{→}_n=mg-ma$, Ruch przyśpieszony w górę to ruch opóźniony w dół, a przyśpieszony w dół, to opóźniony w górę <br> $\\F↖{→}_n$ siła nacisku, $\\g$ przyśpieszenie ziemskie, $\\a$ przyśpieszenie windy, $\\m$ masa obiektu w windzie. Jeżeli $\\a=g$ to występuje nieważkość"))


/*

  przyspieszenie_dosrodkowe $a_d↖{→}=v^2/r$ $[m/s^2]$ <br> $a_d↖{→}$ przyśpieszenie dośrodkowe, $r$ promień, $v$ prędkość
    pierwsza_zasada_dynamiki Jeżeli $F↖{→}_w=0$ to w izolowanym układzie ciało/punkt materialny spoczywa, lub porusza się ruchem jednostajnym, $F↖{→}_w$ siła wypadkowa
    druga_zasada_dynamiki $a↖{→}=F↖{→}/m [{{kg*m/{s^2}}/{kg}=N/{kg}]$ <br> $F↖{→}={∆p↖{→}}/{t}$ <br> $p↖{→}=mv↖{→}$ $[kg*{m/s}]$ <br> $F↖{→}$ siła, $a↖{→}$ przyśpieszenie, $m$ masa, $∆p$ zmiana pędu, $t$ czas, $N$ Newton, jednostka siły <br> $p↖{→}=p↖{→}_1+p↖{→}_{2}...p↖{→}_n={const}↖{→}$ <br> Suma pędów ciał wchodzących w skład układu izolowanego jest stała. <br> $p↖{→}$ pęd, $v↖{→}$ prędkość, $m$ masa, $p↖{→}_c$ pęd całkowity
    trzecia_zasada_dynamiki $F↖{→}_{AB}=-F↖{→}_{BA}$ <br> $F↖{→}_{AB}$ siła z jaką ciało A działa na ciało B, $F↖{→}_{BA}$ siła z jaką ciało B działa na ciało
    dynamiczne_rownania_ruchu Dla dwóch bloczków o masach $m_1$ i $m_2$ połączonych nierozciągliwą, lekką nitką, bez siły tarcia ciągniętych siłą $F↖{→}$, zaczepioną o drugi bloczek, o zwrocie skierowanym w prawo <br> $F_{w1}=F_n$, $F_{w2}=F-F_n$ <br> $m_1a=F_n$, $m_2a=F-F_n$ <br>  $m_1a+m_2a=F ⇒ a(m_1+m_2)=F ⇒$ $a=F/{m_1+m_2}$ <br> $F_{w1}$, $F_{w2}$ siła wypadkowa pierwsza u druga, $F_n$ siła naciągu miedzy bloczkami, $a_1$, $a_2$ przyśpieszenie pierwszego, drugie ciała, $a$ przyśpieszenie układu
    tarcie $T=μF_n$ <br> $T↖{→}_s=μ_sF_n$, $T↖{→}_k=μ_kF_n$ <br> $T↖{→}$ tarcie, $μ$ współczynnik tarcia, $F_n$ siła nacisku, $T↖{→}_s$ tarcie statyczne, $μ_s$ wsp. tarcia statycznego, $T↖{→}_k$ tarcie kinetyczne $μ_k$ wsp. tarcia kinetycznego <br> $T_{smax}>T_{kmax}$
    rownania_ruchu_dyn Dla ciała leżącego na boku $S$ równi pochyłej o krótszej przyprostokątnej $h$ i dłuższej $l$, przeciwprostokątnej $S$, oraz kącie $α$, dla którego $sinα=h/s$ <br> $F↖{→}_g=mg$,  $sinα=F_s/F_g ⇒ F↖{→}_s=mgsinα$, $cosα=F_n/F_g ⇒ F↖{→}_n=mgcosα$, $T↖{→}=μmgcosα$ <br> $F↖{→}_n$ siła nacisku, $F↖{→}_s$ siła zsuwająca, $F↖{→}_g$ siła grawitacji, $T↖{→}$ siła tarcia, $μ$ wsp. tarcia na równi, na ciało wjeżdżające na równię działa jedynie siła zsuwająca.\']]></string>
    niewazkosc_winda Dla windy jadącej w górę $F↖{→}_n=mg+ma$, Dla windy jadącej w dół $F↖{→}_n=mg-ma$, Ruch przyśpieszony w górę to ruch opóźniony w dół, a przyśpieszony w dół, to opóźniony w górę <br> $F↖{→}_n$ siła nacisku, $g$ przyśpieszenie ziemskie, $a$ przyśpieszenie windy, $m$ masa obiektu w windzie. Jeżeli $a=g$ to występuje nieważkość

*/
    }

    private fun loadData() {
        var disposable = equationsRepository.getAllEquations()
                .subscribeOn(AppSchedulersProvider.ioScheduler())
                .subscribe(
                        {
                            addEquations(it)
                        },
                        {
                            // error here
                        }
                )
    }

    private fun addEquations(equations: List<Equation>) {
        rawEquations.clear()
        rawEquations.addAll(equations)
        isDataLoaded = true
        filterEquations("Kinematyka")
    }

    fun filterEquations(filtering: String) {
        this.equations.clear()
        this.equations.addAll(rawEquations.filter { equation -> equation.section == filtering })
    }

    fun openFlashCards() {
        flashCardsEvent.call()
    }


    //val equations : ObservableList<Equation> = ObservableArrayList()

    /**
     * Backend ->
     * */
}

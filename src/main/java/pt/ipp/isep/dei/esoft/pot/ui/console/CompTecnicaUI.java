/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package It1;

import java.util.List;
import pt.ipp.isep.dei.esoft.pot.controller.EspecificarCompetenciaTecnicaController;
import pt.ipp.isep.dei.esoft.pot.model.AreaAtividade;
import pt.ipp.isep.dei.esoft.pot.ui.console.utils.Utils;
/**
 *
 * @author pokem
 */
public class CompTecnicaUI {
    private CompTecnicaController m_controller;
    
    public CompTecnicaUI(){
        m_controller = new CompTecnicaController();
    }

    public void run(){
        System.out.println("\nEspecificar Competência Técnica:");

        if(introduzDados()){
            apresentaDados();

            if (Utils.confirma("Confirma os dados introduzidos? (S/N)")) {
                if (m_controller.registaCompTecnica()) {
                    System.out.println("Registo efetuado com sucesso.");
                } else {
                    System.out.println("Não foi possivel concluir o registo com sucesso.");
                }
            }
        } else {
            System.out.println("Ocorreu um erro. Operação cancelada.");
        }
    }
    
    private boolean introduzDados() {
        String strId = Utils.readLineFromConsole("Id: ");
        String strDescricaoBreve = Utils.readLineFromConsole("Descrição Breve: ");
        String strDescricaoDetalhada = Utils.readLineFromConsole("Descrição Detalhada: ");
        
        List<AreaAtividade> lc = m_controller.getAreasAtividade();
        
        String areaId = "";
        AreaAtividade area = (AreaAtividade)Utils.apresentaESeleciona(lc, "Selecione a Área de Atividaade a que é referente esta Competência Técnica:");
        if (area != null)
            areaId = area.getCodigo();
        
        return m_controller.novaCompTecnica(strId, strDescricaoBreve,strDescricaoDetalhada,areaId);
    }
    
    private void apresentaDados(){
        System.out.println("\nCompetência Técnica:\n" + m_controller.getCompTecnicaString());
    }
      
}
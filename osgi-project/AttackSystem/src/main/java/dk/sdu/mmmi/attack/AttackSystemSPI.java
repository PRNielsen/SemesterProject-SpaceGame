/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.attack;

import dk.sdu.mmmi.common.data.World;

/**
 *
 * @author Daniel Tran
 */
public interface AttackSystemSPI {
    void performAttack(World world);
    
}

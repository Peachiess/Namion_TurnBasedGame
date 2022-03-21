package com.example.namion_turnbasedgame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    TextView txtHeroName, txtMonsName, txtHeroHP, txtMonsHP, txtHeroMP, txtMonsMP, txtHeroDPS, txtMonsDPS, txtLog;
    Button btnNextTurn;
    ImageButton skill1, skill2, skill3, skill4;

    //Hero Stats
    String heroName = "Andy Sevor";
    int heroHP = 2500;
    int heroMP = 2000;
    int heroMinDamage = 150;
    int heroMaxDamage = 200;

    //Monster Stats
    String monsName = "Dark elf mage";
    int monsterHP = 2000;
    int monsterMP = 500;
    int monsterMinDamage = 50;
    int monsterMaxDamage = 150;
    //Game Turn
    int turnNumber = 1;

    boolean disabledstatus = false;
    int statuscounter = 0;
    int buttoncounter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //XML isd for button and text

        txtHeroName = findViewById(R.id.txtHeroName);
        txtMonsName = findViewById(R.id.txtMonsName);
        txtHeroHP = findViewById(R.id.txtHeroHP);
        txtMonsHP = findViewById(R.id.txtMonsHP);
        txtHeroMP = findViewById(R.id.txtHeroMP);
        txtMonsMP = findViewById(R.id.txtMonsMP);
        btnNextTurn = findViewById(R.id.btnNextTurn);
        txtHeroDPS = findViewById(R.id.txtHeroDPS);
        txtMonsDPS = findViewById(R.id.txtMonsDPS);

        txtLog = findViewById(R.id.txtCombatLog);

        txtHeroName.setText(heroName);
        txtHeroHP.setText(String.valueOf(heroHP));
        txtHeroMP.setText(String.valueOf(heroMP));

        txtMonsName.setText(monsName);
        txtMonsHP.setText(String.valueOf(monsterHP));
        txtMonsMP.setText(String.valueOf(monsterMP));

        skill1 = findViewById(R.id.btnSkill1);
        skill2 = findViewById(R.id.btnSkill2);
        skill3 = findViewById(R.id.btnSkill3);
        skill4 = findViewById(R.id.btnSkill4);


        //button onClick Listener
        btnNextTurn.setOnClickListener(this);
        skill1.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        txtLog.findViewById(R.id.txtCombatLog);

        Random randomizer = new Random();
        int herodps = randomizer.nextInt(heroMaxDamage - heroMinDamage) + heroMinDamage;
        int monsdps = randomizer.nextInt(monsterMaxDamage - monsterMinDamage) + monsterMinDamage;

        if (turnNumber % 2 != 1) {//if it is enemy's turn, disable button
            skill1.setEnabled(false);
        } else if (turnNumber % 2 == 1) {
            skill1.setEnabled(true);
        }

        if (buttoncounter > 0) {
            skill1.setEnabled(false);
            //buttoncounter--;
        } else if (buttoncounter == 0) {
            skill1.setEnabled(true);
        }

        switch (view.getId()) {
            case R.id.btnSkill1:

                monsterHP = monsterHP - 350;
                turnNumber++;
                txtMonsHP.setText(String.valueOf(monsterHP));
                btnNextTurn.setText("Next Turn (" + String.valueOf(turnNumber) + ")");

                txtLog.setText("Our Hero" + String.valueOf(heroName) + "used punch!. It dealt " + String.valueOf(240) + "damage to the enemy. The enemy is stunned for 3 turns");

                disabledstatus = true;
                statuscounter = 4;

            case R.id.btnSkill2:

                monsterHP = monsterHP - 350;
                turnNumber++;
                txtMonsHP.setText(String.valueOf(monsterHP));
                btnNextTurn.setText("Next Turn (" + String.valueOf(turnNumber) + ")");

                txtLog.setText("Our Hero" + String.valueOf(heroName) + "used power up!. It dealt " + String.valueOf(300) + "damage to the enemy.");

                disabledstatus = true;
                statuscounter = 4;

            case R.id.btnSkill3:

                monsterHP = monsterHP - 350;
                turnNumber++;
                txtMonsHP.setText(String.valueOf(monsterHP));
                btnNextTurn.setText("Next Turn (" + String.valueOf(turnNumber) + ")");

                txtLog.setText("Our Hero" + String.valueOf(heroName) + "used slash!. It dealt " + String.valueOf(350) + "critical damage to the enemy.");

                disabledstatus = true;
                statuscounter = 4;

            case R.id.btnSkill4:

                monsterHP = monsterHP - 350;
                turnNumber++;
                txtMonsHP.setText(String.valueOf(monsterHP));
                btnNextTurn.setText("Next Turn (" + String.valueOf(turnNumber) + ")");

                txtLog.setText("Our Hero" + String.valueOf(heroName) + "used flash!. It " + String.valueOf(0) + "blinds the enemy for 3 turns");

                disabledstatus = true;
                statuscounter = 4;

                if (monsterHP > 0) { //even
                    txtLog.setText("Our Hero" + String.valueOf(heroName) + "dealt" + String.valueOf(herodps) + "damage to the enemy. The player won!");
                    heroHP = 2500;
                    monsterHP = 2000;
                    turnNumber = 1;
                    btnNextTurn.setText("Reset Game");
                }
                buttoncounter = 12;


                break;
            case R.id.btnNextTurn:
                //

                if (turnNumber % 2 == 1) { //odd
                    monsterHP = monsterHP - herodps;
                    turnNumber++;
                    txtMonsHP.setText(String.valueOf(monsterHP));
                    btnNextTurn.setText("Next Turn (" + String.valueOf(turnNumber) + ")");

                    txtLog.setText("Our Hero" + String.valueOf(heroName) + "used punch!. It dealt" + String.valueOf(350) + "damage to the enemy. the enemy is stunned for 3 turns");

                    if (monsterHP < 0) { // even
                        txtLog.setText("Our Hero" + String.valueOf(heroName) + "dealt" + String.valueOf(herodps) + "damage to the enemy. The player won!");
                        heroHP = 2500;
                        monsterHP = 2000;
                        turnNumber = 1;
                        buttoncounter = 0;
                        btnNextTurn.setText("Reset Game");
                    }

                    // if (statuscounter>0){ //if the enemy is still stunned, reduce the stun for 1 turn
                    // statuscounter--;
                    // if (statuscounter==0){
                    // disabledstatus=false;
                    // }
                    //  }
                    buttoncounter--;
                } else if (turnNumber % 2 != 1) { //even

                    if (disabledstatus == true) {
                        txtLog.setText("The enemy is still stunned for" + String.valueOf(statuscounter) + "turns");
                        statuscounter--;
                        turnNumber++;
                        btnNextTurn.setText("Next Turn (" + String.valueOf(turnNumber) + ")");
                        if (statuscounter == 0) {
                            disabledstatus = false;
                            }
                        } else {
                            heroHP = heroHP - monsdps;
                            turnNumber++;
                            txtHeroHP.setText(String.valueOf(heroHP));
                            btnNextTurn.setText("Next Turn (" + String.valueOf(turnNumber) + ")");

                            txtLog.setText("The monster" + String.valueOf(monsName) + "dealt" + String.valueOf(monsdps) + "damage to the enemy.");

                            if (heroHP < 0) {
                                txtLog.setText("The monster" + String.valueOf(monsName) + "dealt" + String.valueOf(monsdps) + "damage to the  enemy. Game Over");
                                monsterHP = 2000;
                                turnNumber = 1;
                                buttoncounter = 0;
                                btnNextTurn.setText("Reset Game");
                            }
                        }
                        // buttoncounter--;
                    }
                    break;
                }

        }
    }
package com.cardmicroservice;

import com.cardmicroservice.model.Card;
import com.cardmicroservice.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CardInitializer implements CommandLineRunner {

    @Autowired
    private CardRepository cardRepository;

    @Override
    public void run(String... args) throws Exception {
        // Initialisation de la table "Card" en ajoutant des enregistrements
        if (cardRepository.count() == 0) {

            List<Card> cards = new ArrayList<>();

            // Create and add cards
            Card card1 = new Card(
                    "Luffy",
                    300,
                    20,
                    280,
                    150,
                    "MUGIWARAS",
                    "Le futur roi des pirates !",
                    1500,
                    "/assets/img/cards/luffy.png"
            );

            cards.add(card1);

            Card card2 = new Card(
                    "Zoro",
                    250,
                    18,
                    200,
                    200,
                    "MUGIWARAS",
                    "Le roi des enfers !",
                    1400,
                    "/assets/img/cards/zoro.jpeg"
            );

            cards.add(card2);

            Card card3 = new Card(
                    "Sanji",
                    220,
                    15,
                    150,
                    300,
                    "MUGIWARAS",
                    "La jambe noire !",
                    1200,
                    "/assets/img/cards/sanji.jpg"
            );

            cards.add(card3);

            Card card4 = new Card(
                    "Jinbe",
                    230,
                    15,
                    170,
                    230,
                    "MUGIWARAS",
                    "Le paladin des mers !",
                    1300,
                    "/assets/img/cards/jinbe.jpg"
            );

            cards.add(card4);

            Card card5 = new Card(
                    "Ussop",
                    130,
                    50,
                    70,
                    100,
                    "MUGIWARAS",
                    "Le tireur d'élite !",
                    500,
                    "/assets/img/cards/ussop.jpg"
            );

            cards.add(card5);

            Card card6 = new Card(
                    "Nami",
                    150,
                    60,
                    90,
                    120,
                    "MUGIWARAS",
                    "La voleuse !",
                    550,
                    "/assets/img/cards/nami.jpg"
            );

            cards.add(card6);

            Card card7 = new Card(
                    "Robin",
                    200,
                    15,
                    140,
                    130,
                    "MUGIWARAS",
                    "L'enfant démoniaque",
                    800,
                    "/assets/img/cards/robin.jpg"
            );

            cards.add(card7);

            Card card8 = new Card(
                    "Chopper",
                    130,
                    20,
                    70,
                    110,
                    "MUGIWARAS",
                    "Le docteur des MUGIWARAS !",
                    50,
                    "/assets/img/cards/chopper.jpg"
            );

            cards.add(card8);

            Card card9 = new Card(
                    "Franky",
                    180,
                    25,
                    130,
                    135,
                    "MUGIWARAS",
                    "Le cyborg !",
                    600,
                    "/assets/img/cards/franky.jpeg"
            );

            cards.add(card9);

            Card card10 = new Card(
                    "Brook",
                    350,
                    16,
                    140,
                    140,
                    "MUGIWARAS",
                    "L'épée chevaleresque !",
                    550,
                    "/assets/img/cards/brook.jpg"
            );

            cards.add(card10);

            Card card11 = new Card(
                    "Shanks",
                    170,
                    70,
                    400,
                    200,
                    "REDS",
                    "Le roux",
                    1800,
                    "/assets/img/cards/shanks.jpg"
            );

            cards.add(card11);


            cardRepository.saveAll(cards);

        }

    }
}
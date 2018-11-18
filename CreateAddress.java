package edu.stanford.crypto.cs251.transactions;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

import org.bitcoinj.core.Address;
import org.bitcoinj.core.Base58;
import org.bitcoinj.core.DumpedPrivateKey;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;

//package com.waferthin.bitcoinj;
//
//import com.google.bitcoin.core.ECKey;
//import com.google.bitcoin.core.NetworkParameters;
//import com.google.bitcoin.core.Address;

public class CreateAddress {
	public static final char[] ALPHABET = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz".toCharArray();
	private static final char ENCODED_ZERO = ALPHABET[0];
	private static final int[] INDEXES = new int[128];
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		
		Scanner in = new Scanner(System.in);
        // use test net by default
        String net = "test";
        
        if (args.length >= 1 && (args[0].equals("test") || args[0].equals("prod"))) {
            net = args[0];
            System.out.println("Using " + net + " network.");
        }
        
        // create a new EC Key ...
        ECKey key = new ECKey();

        // ... and look at the key pair
        System.out.println("We created key:\n" + key);
        
        // either test or production net are possible
        final NetworkParameters netParams;
        
        if (net.equals("prod")) {
            netParams = NetworkParameters.prodNet();
        } else {
            netParams = NetworkParameters.testNet();
        }
        
        // get valid Bitcoin address from public key
        Address addressFromKey = key.toAddress(netParams);
        
        DumpedPrivateKey myPrivateKey = key.getPrivateKeyEncoded(netParams);
        byte[] mPKinBytes = key.getPrivKeyBytes();
        byte[] dumpedPK = myPrivateKey.toString().getBytes();
        
        byte[] b = addressFromKey.toString().getBytes();
        
        String base58Address = Base58.encode(b);
        System.out.println("On the " + net + " network, we can use this address:\n\n" + addressFromKey);
        System.out.println("Base58 Address: " + base58Address);
        byte[] baseReverse = Base58.decode(base58Address);
        System.out.println("Base 58 Reverse: " + new String(baseReverse));
        
        String pwd = "1234";
        
        System.out.println("Please input password: ");
        String userPwd = in.next();
        
        if(!userPwd.equals(pwd)) {
        	System.out.println("Password is incorrect!");
        } else {
        	System.out.println("...");
//        	PayToPubKeyHash(netParams,"sdf", userPwd);
        }
        
    }
    
}
